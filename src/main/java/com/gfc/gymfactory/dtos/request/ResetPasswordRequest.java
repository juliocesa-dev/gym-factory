package com.gfc.gymfactory.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPasswordRequest(
        @Email @NotBlank String email,
        @NotBlank @Size(min = 6, max = 6) String code,
        @NotBlank @Size(min = 6) String newPassword
) {}
