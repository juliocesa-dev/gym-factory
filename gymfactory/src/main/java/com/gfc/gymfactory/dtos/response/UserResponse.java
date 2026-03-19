package com.gfc.gymfactory.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class UserResponse {
    private UUID id;
    private String name;
    private String phoneNumber;
    private String email;
}
