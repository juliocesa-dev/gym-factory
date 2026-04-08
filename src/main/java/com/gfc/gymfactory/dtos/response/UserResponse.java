package com.gfc.gymfactory.dtos.response;

import com.gfc.gymfactory.domain.enums.Role;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
        UUID id,
        String name,
        String phoneNumber,
        String email,
        Role role,
        Boolean active
) {}