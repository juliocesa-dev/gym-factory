package com.gfc.gymfactory.mappers;

import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.dtos.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }

}
