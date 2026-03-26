package com.gfc.gymfactory.dtos.response;

import java.util.UUID;

public record AuthResponse(
        UUID id,
        String name,
        String email,
        String phoneNumber,
        String token
) {}