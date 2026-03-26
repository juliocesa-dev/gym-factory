package com.gfc.gymfactory.domain.enums;

import lombok.Getter;

@Getter
public enum RoutineGoal {
    FAT_LOSS("Redução de Gordura"),
    HYPERTROPHY("Hipertrofia"),
    HYPERTROPHY_FAT_LOSS("Hipertrofia/Redução de Gordura"),
    STRENGTH("Força"),
    ENDURANCE("Resistência"),
    FLEXIBILITY("Flexibilidade"),
    HEALTH("Saúde e Bem-estar");

    private final String label;

    RoutineGoal(String label) {
        this.label = label;
    }
}