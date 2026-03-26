package com.gfc.gymfactory.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WorkoutRequest(
        @NotBlank String name,
        String description,
        @NotNull Long routineId
) {}