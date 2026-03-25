package com.gfc.gymfactory.domain.enums;

import lombok.Getter;

@Getter
public enum RoutineStatus {
    REQUESTED("Solicitado"),
    IN_PROGRESS("Em andamento"),
    DONE("Concluído");

    private final String label;

    RoutineStatus(String label) {
        this.label = label;
    }
}