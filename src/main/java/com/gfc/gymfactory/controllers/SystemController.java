package com.gfc.gymfactory.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController {

    private final BuildProperties buildProperties;

    @Value("${spring.profiles.active}")
    private String environment;

    @GetMapping("/info")
    public Map<String, Object> info() {
        return Map.of(
                "status", "UP",
                "app", Objects.requireNonNull(buildProperties.getName()),
                "version", Objects.requireNonNull(buildProperties.getVersion()),
                "buildTime", Objects.requireNonNull(buildProperties.getTime()),
                "environment", environment
        );
    }

}