package com.gfc.gymfactory.dtos.request;

import jakarta.validation.constraints.NotNull;

public record WorkoutExerciseUpdateRequest(
        @NotNull Integer sets,
        @NotNull Integer reps,
        Integer restSeconds,
        Double weight,
        String notes
) {}