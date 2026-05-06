package com.gfc.gymfactory.services;

import com.gfc.gymfactory.config.security.SecurityUtils;
import com.gfc.gymfactory.domain.entities.PasswordResetToken;
import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.domain.enums.Role;
import com.gfc.gymfactory.dtos.request.ForgotPasswordRequest;
import com.gfc.gymfactory.dtos.request.LoginRequest;
import com.gfc.gymfactory.dtos.request.RegisterRequest;
import com.gfc.gymfactory.dtos.request.ResetPasswordRequest;
import com.gfc.gymfactory.dtos.response.AuthResponse;
import com.gfc.gymfactory.dtos.response.UserResponse;
import com.gfc.gymfactory.exception.ApiException;
import com.gfc.gymfactory.factories.UserFactory;
import com.gfc.gymfactory.mappers.UserMapper;
import com.gfc.gymfactory.repositories.PasswordResetTokenRepository;
import com.gfc.gymfactory.repositories.UserRepository;
import com.gfc.gymfactory.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserFactory userFactory;
    private final UserValidator userValidator;
    private final EmailService emailService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String CHARSET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

    public UserResponse register(RegisterRequest request) {
        validateRegisterPermission(getCallerRole(), request.role());

        userValidator.throwIfEmailExists(request.email());

        String rawPassword = generatePassword();
        User user = userFactory.createUser(request, rawPassword);
        userRepository.save(user);

        try {
            emailService.sendWelcomeEmail(user.getEmail(), user.getName(), rawPassword);
        } catch (Exception e) {
            log.error("Falha ao enviar email de boas-vindas para {}: {}", user.getEmail(), e.getMessage());
        }

        return userMapper.toUserResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmailOrThrow(request.email(), "Credenciais inválidas");

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ApiException("Credenciais inválidas", HttpStatus.UNAUTHORIZED);
        }

        if (!user.getActive()) {
            throw new ApiException("Usuário desabilitado", HttpStatus.FORBIDDEN);
        }

        return buildAuthResponse(user);
    }

    public void forgotPassword(ForgotPasswordRequest request) {
        userRepository.findByEmail(request.email()).ifPresent(user -> {
            String code = generateResetCode();

            PasswordResetToken token = PasswordResetToken.builder()
                    .email(user.getEmail())
                    .code(code)
                    .expiresAt(LocalDateTime.now().plusMinutes(15))
                    .used(false)
                    .createdAt(LocalDateTime.now())
                    .build();

            passwordResetTokenRepository.save(token);

            try {
                emailService.sendPasswordResetCode(user.getEmail(), user.getName(), code);
            } catch (Exception e) {
                log.error("Falha ao enviar código de recuperação para {}: {}", user.getEmail(), e.getMessage());
            }
        });
    }

    public void resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByEmailOrThrow(request.email(), "Usuário não encontrado");

        PasswordResetToken token = passwordResetTokenRepository
                .findTopByEmailOrderByCreatedAtDesc(request.email())
                .orElseThrow(() -> new ApiException("Código inválido ou expirado", HttpStatus.BAD_REQUEST));

        if (token.getUsed()) {
            throw new ApiException("Código já utilizado", HttpStatus.BAD_REQUEST);
        }
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ApiException("Código expirado", HttpStatus.BAD_REQUEST);
        }
        if (!token.getCode().equals(request.code())) {
            throw new ApiException("Código inválido", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

        token.setUsed(true);
        passwordResetTokenRepository.save(token);
    }

    private void validateRegisterPermission(Role caller, Role target) {
        boolean allowed = switch (caller) {
            case ADMIN -> true;
            case RECEPTIONIST -> target != Role.ADMIN;
            case INSTRUCTOR -> target == Role.STUDENT;
            case STUDENT -> false;
        };

        if (!allowed) {
            throw new ApiException("Sem permissão para cadastrar este tipo de usuário", HttpStatus.FORBIDDEN);
        }
    }

    private Role getCallerRole() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new ApiException("Não autenticado", HttpStatus.UNAUTHORIZED);
        }
        return auth.getAuthorities().stream()
                .findFirst()
                .map(a -> Role.valueOf(Objects.requireNonNull(a.getAuthority()).replace("ROLE_", "")))
                .orElseThrow(() -> new ApiException("Não autenticado", HttpStatus.UNAUTHORIZED));
    }

    private String generatePassword() {
        return RANDOM.ints(6, 0, CHARSET.length())
                .mapToObj(i -> String.valueOf(CHARSET.charAt(i)))
                .collect(Collectors.joining());
    }

    private String generateResetCode() {
        return String.format("%06d", RANDOM.nextInt(1_000_000));
    }

    private AuthResponse buildAuthResponse(User user) {
        String token = securityUtils.generateToken(user);
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), token);
    }
}
