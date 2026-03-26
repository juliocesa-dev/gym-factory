package com.gfc.gymfactory.validators;

import com.gfc.gymfactory.exception.ApiException;
import com.gfc.gymfactory.domain.entities.Routine;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WorkoutValidator {

    public void throwIfRoutineIsTemplate(Routine routine) {
        if (routine.getIsTemplate()) {
            throw new ApiException(
                    "Não é possível adicionar treino em rotina template",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}