package com.gfc.gymfactory.dtos.response;

import com.gfc.gymfactory.domain.enums.ExerciseCategory;
import com.gfc.gymfactory.domain.enums.MuscleGroup;

public record ExerciseResponse(
        Long id,
        String name,
        String description,
        MuscleGroup muscleGroup,
        ExerciseCategory category
) {}