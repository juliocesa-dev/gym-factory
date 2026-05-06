package com.gfc.gymfactory.dtos.response;

import java.util.List;

public record WorkoutDetailResponse(
        Long id,
        String name,
        String description,
        List<WorkoutExerciseResponse> exercises
) {}
