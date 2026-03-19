package com.gfc.gymfactory.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterRequest(
        @NotBlank String name,
        @Email @NotBlank String email,
        @NotBlank String password,
        @Pattern(
                regexp = "^\\d{2}9\\d{8}$",
                message = "Telefone inválido. Use o formato: DDD9XXXXXXXX (ex: 81912345678)"
        )
        String phoneNumber
) {}