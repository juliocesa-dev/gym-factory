package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.dtos.request.LoginRequest;
import com.gfc.gymfactory.dtos.request.RegisterRequest;
import com.gfc.gymfactory.dtos.request.RegisterStaffRequest;
import com.gfc.gymfactory.dtos.response.AuthResponse;
import com.gfc.gymfactory.dtos.response.UserResponse;
import com.gfc.gymfactory.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/register-staff")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse registerStaff(@RequestBody @Valid RegisterStaffRequest request) {
        return authService.registerStaff(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}
