package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.dtos.request.ForgotPasswordRequest;
import com.gfc.gymfactory.dtos.request.LoginRequest;
import com.gfc.gymfactory.dtos.request.RegisterRequest;
import com.gfc.gymfactory.dtos.request.ResetPasswordRequest;
import com.gfc.gymfactory.dtos.response.AuthResponse;
import com.gfc.gymfactory.dtos.response.UserResponse;
import com.gfc.gymfactory.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Autenticação e gerenciamento de contas")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Cadastrar usuário")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'RECEPTIONIST', 'ADMIN')")
    public UserResponse register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @Operation(summary = "Solicitar código de recuperação de senha")
    @PostMapping("/forgot-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {
        authService.forgotPassword(request);
    }

    @Operation(summary = "Redefinir senha com código")
    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        authService.resetPassword(request);
    }
}
