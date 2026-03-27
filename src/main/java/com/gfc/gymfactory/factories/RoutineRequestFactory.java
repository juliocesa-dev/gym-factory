package com.gfc.gymfactory.factories;

import com.gfc.gymfactory.domain.entities.RoutineRequest;
import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.domain.enums.RoutineRequestStatus;
import com.gfc.gymfactory.dtos.request.RoutineRequestCreateDto;
import org.springframework.stereotype.Component;

@Component
public class RoutineRequestFactory {

    public RoutineRequest create(RoutineRequestCreateDto dto, User student) {
        return RoutineRequest.builder()
                .student(student)
                .goal(dto.goal())
                .difficulty(dto.difficulty())
                .observations(dto.observations())
                .status(RoutineRequestStatus.PENDING)
                .build();
    }
}