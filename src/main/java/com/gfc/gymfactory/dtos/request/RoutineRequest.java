package com.gfc.gymfactory.dtos.request;

import com.gfc.gymfactory.domain.enums.RoutineDifficulty;
import com.gfc.gymfactory.domain.enums.RoutineGoal;
import com.gfc.gymfactory.domain.enums.RoutineStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record RoutineRequest(
        @NotBlank String name,
        String description,
        @NotNull RoutineStatus status,
        @NotNull RoutineGoal goal,
        @NotNull RoutineDifficulty difficulty,
        @NotNull Boolean isTemplate,
        LocalDate startDate,
        LocalDate endDate,
        @NotNull UUID instructorId,
        UUID studentId
) {}