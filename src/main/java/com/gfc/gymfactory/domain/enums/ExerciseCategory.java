package com.gfc.gymfactory.domain.enums;

import lombok.Getter;

@Getter
public enum ExerciseCategory {
    MUSCULACAO("Musculação"),
    AEROBICO("Aeróbico"),
    FUNCIONAL("Funcional"),
    ALONGAMENTO("Alongamento"),
    MOBILIDADE("Mobilidade"),
    TRABALHO("Trabalho");

    private final String label;

    ExerciseCategory(String label) {
        this.label = label;
    }
}