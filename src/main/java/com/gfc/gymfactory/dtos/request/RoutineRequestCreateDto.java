package com.gfc.gymfactory.dtos.request;

import com.gfc.gymfactory.domain.enums.RoutineDifficulty;
import com.gfc.gymfactory.domain.enums.RoutineGoal;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RoutineRequestCreateDto(
        @NotNull UUID studentId,
        @NotNull RoutineGoal goal,
        @NotNull RoutineDifficulty difficulty,
        String observations
) {}