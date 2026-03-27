package com.gfc.gymfactory.dtos.response;

import com.gfc.gymfactory.domain.enums.RoutineDifficulty;
import com.gfc.gymfactory.domain.enums.RoutineGoal;
import com.gfc.gymfactory.domain.enums.RoutineRequestStatus;

import java.time.LocalDateTime;

public record RoutineRequestRejectResponse(
        Long id,
        UserResponse student,
        RoutineGoal goal,
        RoutineDifficulty difficulty,
        String observations,
        RoutineRequestStatus status,
        String rejectionReason,
        LocalDateTime createdAt
) {}