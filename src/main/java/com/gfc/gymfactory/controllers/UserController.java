package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.dtos.response.UserResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public PageResponse<UserResponse> findUsers(Pageable pageable) {
        return userService.findUsers(pageable);
    }

    @GetMapping("/{id}")
    public UserResponse findUserByID(@PathVariable UUID id){
        return userService.findUserByID(id);
    }

}
