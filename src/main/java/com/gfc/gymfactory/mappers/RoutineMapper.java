package com.gfc.gymfactory.mappers;

import com.gfc.gymfactory.domain.entities.Routine;
import com.gfc.gymfactory.dtos.request.RoutineRequest;
import com.gfc.gymfactory.dtos.request.RoutineUpdateRequest;
import com.gfc.gymfactory.dtos.response.RoutineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoutineMapper {

    private final UserMapper userMapper;

    public Routine toEntity(RoutineRequest request) {
        return Routine.builder()
                .name(request.name())
                .description(request.description())
                .status(request.status())
                .goal(request.goal())
                .difficulty(request.difficulty())
                .isTemplate(request.isTemplate())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();
    }

    public RoutineResponse toResponse(Routine routine) {
        return new RoutineResponse(
                routine.getId(),
                routine.getName(),
                routine.getDescription(),
                routine.getStatus(),
                routine.getGoal(),
                routine.getDifficulty(),
                routine.getIsTemplate(),
                routine.getStartDate(),
                routine.getEndDate(),
                userMapper.toUserResponse(routine.getInstructor()),
                routine.getStudent() != null
                        ? userMapper.toUserResponse(routine.getStudent())
                        : null,
                routine.getCreatedAt()
        );
    }

    public void update(Routine routine, RoutineUpdateRequest request) {
        routine.setName(request.name());
        routine.setDescription(request.description());
        routine.setStatus(request.status());
        routine.setGoal(request.goal());
        routine.setDifficulty(request.difficulty());
        if (!routine.getIsTemplate()) {
            routine.setIsTemplate(request.isTemplate());
        }
        routine.setStartDate(request.startDate());
        routine.setEndDate(request.endDate());
    }
}