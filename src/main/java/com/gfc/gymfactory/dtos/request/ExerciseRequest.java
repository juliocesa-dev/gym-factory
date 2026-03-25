package com.gfc.gymfactory.dtos.request;

import com.gfc.gymfactory.domain.enums.ExerciseCategory;
import com.gfc.gymfactory.domain.enums.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExerciseRequest(
        @NotBlank String name,
        String description,
        @NotNull MuscleGroup muscleGroup,
        @NotNull ExerciseCategory category
) {}