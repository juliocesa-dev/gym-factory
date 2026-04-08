package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.dtos.response.UserResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public UserResponse findUserByID(@PathVariable UUID id) {
        return userService.findUserByID(id);
    }

    @GetMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public PageResponse<UserResponse> findEmployeesByActive(@RequestParam boolean active, Pageable pageable) {
        return userService.findEmployeesByActive(active, pageable);
    }

    @PatchMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void enableEmployee(@PathVariable UUID id) {
        userService.enableEmployee(id);
    }

    @PatchMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void disableEmployee(@PathVariable UUID id) {
        userService.disableEmployee(id);
    }
}
