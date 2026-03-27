package com.gfc.gymfactory.domain.enums;

import lombok.Getter;

@Getter
public enum RoutineRequestStatus {
    PENDING("Pendente"),
    ACCEPTED("Aceita"),
    REJECTED("Rejeitada");

    private final String label;

    RoutineRequestStatus(String label) {
        this.label = label;
    }
}

