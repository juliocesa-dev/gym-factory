package com.gfc.gymfactory.validators;

import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.domain.enums.Role;
import com.gfc.gymfactory.dtos.request.RoutineRequest;
import com.gfc.gymfactory.dtos.request.RoutineUpdateRequest;
import com.gfc.gymfactory.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RoutineValidator {

    public void validate(RoutineRequest request) {
        if (request.isTemplate() && request.studentId() != null) {
            throw new ApiException(
                "Rotina template não pode ter aluno associado",
                HttpStatus.BAD_REQUEST
            );
        }
        if (!request.isTemplate() && request.studentId() == null) {
            throw new ApiException(
                "Rotina real deve ter aluno associado",
                HttpStatus.BAD_REQUEST
            );
        }
        if (request.startDate() != null && request.endDate() != null
                && request.endDate().isBefore(request.startDate())) {
            throw new ApiException(
                "Data de término não pode ser anterior à data de início",
                HttpStatus.BAD_REQUEST
            );
        }
    }

    public void validateUpdate(RoutineUpdateRequest request) {
        if (request.startDate() != null && request.endDate() != null
                && request.endDate().isBefore(request.startDate())) {
            throw new ApiException(
                "Data de término não pode ser anterior à data de início",
                HttpStatus.BAD_REQUEST
            );
        }
    }

    public void throwIfNotInstructor(User user) {
        if (user.getRole() == Role.STUDENT) {
            throw new ApiException("Usuário não é instrutor", HttpStatus.BAD_REQUEST);
        }
    }

    public void throwIfNotStudent(User user) {
        if (user.getRole() != Role.STUDENT) {
            throw new ApiException("Usuário não é aluno", HttpStatus.BAD_REQUEST);
        }
    }
}