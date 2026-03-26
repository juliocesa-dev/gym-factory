package com.gfc.gymfactory.dtos.response;

import com.gfc.gymfactory.domain.enums.RoutineDifficulty;
import com.gfc.gymfactory.domain.enums.RoutineGoal;
import com.gfc.gymfactory.domain.enums.RoutineStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RoutineResponse(
        Long id,
        String name,
        String description,
        RoutineStatus status,
        RoutineGoal goal,
        RoutineDifficulty difficulty,
        Boolean isTemplate,
        LocalDate startDate,
        LocalDate endDate,
        UserResponse instructor,
        UserResponse student,
        LocalDateTime createdAt
) {}