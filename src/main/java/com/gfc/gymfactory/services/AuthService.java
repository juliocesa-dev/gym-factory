package com.gfc.gymfactory.services;

import com.gfc.gymfactory.config.security.SecurityUtils;
import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.dtos.request.LoginRequest;
import com.gfc.gymfactory.dtos.request.RegisterRequest;
import com.gfc.gymfactory.dtos.response.AuthResponse;
import com.gfc.gymfactory.dtos.response.UserResponse;
import com.gfc.gymfactory.exception.ApiException;
import com.gfc.gymfactory.factories.UserFactory;
import com.gfc.gymfactory.mappers.UserMapper;
import com.gfc.gymfactory.repositories.UserRepository;
import com.gfc.gymfactory.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserFactory userFactory;
    private final UserValidator userValidator;

    public AuthResponse register(RegisterRequest request) {
        userValidator.throwIfEmailExists(request.email());

        User user = userFactory.createForRegister(request);

        userRepository.save(user);

        return buildAuthResponse(user);
    }

    public UserResponse registerInstructor(RegisterRequest request) {
        userValidator.throwIfEmailExists(request.email());

        User user = userFactory.createForInstructorRegister(request);

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmailOrThrow(request.email(), "Credenciais inválidas");

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ApiException("Credenciais inválidas", HttpStatus.UNAUTHORIZED);
        }

        return buildAuthResponse(user);
    }

    private AuthResponse buildAuthResponse(User user) {
        String token = securityUtils.generateToken(user);
        return new AuthResponse(user.getName(), user.getEmail(), user.getPhoneNumber(), token);
    }
}