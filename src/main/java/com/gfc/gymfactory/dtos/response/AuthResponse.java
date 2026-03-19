package com.gfc.gymfactory.dtos.response;

public record AuthResponse(
        String name,
        String email,
        String phoneNumber,
        String token
) {}