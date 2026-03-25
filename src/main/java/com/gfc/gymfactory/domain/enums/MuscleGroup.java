package com.gfc.gymfactory.domain.enums;

import lombok.Getter;

@Getter
public enum MuscleGroup {
    PEITORAL("Peitoral"),
    DORSAL("Dorsal"),
    BICEPS("Bíceps"),
    TRICEPS("Tríceps"),
    INFERIORES("Inferiores"),
    OMBRO("Ombro"),
    ABDOMEN("Abdômen"),
    ANTEBRACO("Antebraço"),
    AEROBIO("Aeróbio"),
    CORRIDA("Corrida"),
    FUNCIONAL("Funcional"),
    OUTROS("Outros"),
    COMBINADO("Combinado"),
    ALONGAMENTO("Alongamento"),
    MOBILIDADE("Mobilidade"),
    LABORAL("Laboral");

    private final String label;

    MuscleGroup(String label) {
        this.label = label;
    }
}