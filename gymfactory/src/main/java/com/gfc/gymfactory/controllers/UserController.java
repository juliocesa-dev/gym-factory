package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User findUserByID(@PathVariable UUID id){
        return userService.findUserByID(id);
    }

}
