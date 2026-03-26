package com.gfc.gymfactory.dtos.response;

public record WorkoutResponse(
        Long id,
        String name,
        String description,
        Long routineId
) {}