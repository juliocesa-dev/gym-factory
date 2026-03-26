package com.gfc.gymfactory.domain.enums;

import lombok.Getter;

@Getter
public enum RoutineDifficulty {
    BEGINNER("Iniciante"),
    INTERMEDIATE("Intermediário"),
    ADVANCED("Avançado");

    private final String label;

    RoutineDifficulty(String label) {
        this.label = label;
    }
}