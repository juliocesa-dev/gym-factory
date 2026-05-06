package com.gfc.gymfactory.mappers;

import com.gfc.gymfactory.domain.entities.Workout;
import com.gfc.gymfactory.dtos.request.WorkoutRequest;
import com.gfc.gymfactory.dtos.request.WorkoutUpdateRequest;
import com.gfc.gymfactory.dtos.response.WorkoutDetailResponse;
import com.gfc.gymfactory.dtos.response.WorkoutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutMapper {

    private final WorkoutExerciseMapper workoutExerciseMapper;

    public Workout toEntity(WorkoutRequest request) {
        return Workout.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public WorkoutResponse toResponse(Workout workout) {
        return new WorkoutResponse(
                workout.getId(),
                workout.getName(),
                workout.getDescription(),
                workout.getRoutine().getId()
        );
    }

    public WorkoutDetailResponse toDetailResponse(Workout workout) {
        return new WorkoutDetailResponse(
                workout.getId(),
                workout.getName(),
                workout.getDescription(),
                workout.getExercises().stream().map(workoutExerciseMapper::toResponse).toList()
        );
    }

    public void update(Workout workout, WorkoutUpdateRequest request) {
        workout.setName(request.name());
        workout.setDescription(request.description());
    }
}
