package com.gfc.gymfactory.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record WorkoutUpdateRequest(
        @NotBlank String name,
        String description
) {}