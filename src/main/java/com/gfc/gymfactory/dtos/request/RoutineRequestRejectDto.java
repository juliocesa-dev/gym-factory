package com.gfc.gymfactory.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record RoutineRequestRejectDto(
        @NotBlank String rejectionReason
) {}