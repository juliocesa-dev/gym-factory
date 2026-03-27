package com.gfc.gymfactory.mappers;

import com.gfc.gymfactory.domain.entities.RoutineRequest;
import com.gfc.gymfactory.dtos.response.RoutineRequestCreateResponse;
import com.gfc.gymfactory.dtos.response.RoutineRequestRejectResponse;
import com.gfc.gymfactory.dtos.response.RoutineRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoutineRequestMapper {

    private final UserMapper userMapper;

    public RoutineRequestResponse toResponse(RoutineRequest routineRequest) {
        return new RoutineRequestResponse(
                routineRequest.getId(),
                userMapper.toUserResponse(routineRequest.getStudent()),
                routineRequest.getGoal(),
                routineRequest.getDifficulty(),
                routineRequest.getObservations(),
                routineRequest.getStatus(),
                routineRequest.getRejectionReason(),
                routineRequest.getRoutine() != null ? routineRequest.getRoutine().getId() : null,
                routineRequest.getCreatedAt()
        );
    }

    public RoutineRequestCreateResponse toCreateResponse(RoutineRequest routineRequest) {
        return new RoutineRequestCreateResponse(
                routineRequest.getId(),
                userMapper.toUserResponse(routineRequest.getStudent()),
                routineRequest.getGoal(),
                routineRequest.getDifficulty(),
                routineRequest.getObservations(),
                routineRequest.getStatus(),
                routineRequest.getCreatedAt()
        );
    }

    public RoutineRequestRejectResponse toRejectResponse(RoutineRequest routineRequest) {
        return new RoutineRequestRejectResponse(
                routineRequest.getId(),
                userMapper.toUserResponse(routineRequest.getStudent()),
                routineRequest.getGoal(),
                routineRequest.getDifficulty(),
                routineRequest.getObservations(),
                routineRequest.getStatus(),
                routineRequest.getRejectionReason(),
                routineRequest.getCreatedAt()
        );
    }
}