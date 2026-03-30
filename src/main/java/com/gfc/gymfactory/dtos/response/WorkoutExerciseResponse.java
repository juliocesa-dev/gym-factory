package com.gfc.gymfactory.dtos.response;

public record WorkoutExerciseResponse(
        Long id,
        Integer sets,
        Integer reps,
        Integer restSeconds,
        Double weight,
        String notes,
        ExerciseResponse exercise
) {}