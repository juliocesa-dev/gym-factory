package com.gfc.gymfactory.dtos.request;

import com.gfc.gymfactory.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterRequest(
        @NotBlank String name,
        @Email @NotBlank String email,
        @Pattern(
                regexp = "^\\d{2}9\\d{8}$",
                message = "Telefone inválido. Use o formato: DDD9XXXXXXXX (ex: 81912345678)"
        )
        String phoneNumber,
        @NotNull Role role
) {}