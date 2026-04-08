package com.gfc.gymfactory.factories;

import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.domain.enums.Role;
import com.gfc.gymfactory.dtos.request.RegisterRequest;
import com.gfc.gymfactory.dtos.request.RegisterStaffRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    public User createForRegister(RegisterRequest request){
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .role(Role.STUDENT)
                .build();
    }

    public User createForStaffRegister(RegisterStaffRequest request){
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .role(request.role())
                .build();
    }

}
