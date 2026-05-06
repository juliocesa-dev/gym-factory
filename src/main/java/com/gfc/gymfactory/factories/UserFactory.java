package com.gfc.gymfactory.factories;

import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.dtos.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    public User createUser(RegisterRequest request, String rawPassword) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(rawPassword))
                .phoneNumber(request.phoneNumber())
                .role(request.role())
                .build();
    }
}
