package com.gfc.gymfactory.mappers;

import com.gfc.gymfactory.domain.entities.Exercise;
import com.gfc.gymfactory.dtos.request.ExerciseRequest;
import com.gfc.gymfactory.dtos.response.ExerciseResponse;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    public Exercise toEntity(ExerciseRequest request) {
        return Exercise.builder()
                .name(request.name())
                .description(request.description())
                .muscleGroup(request.muscleGroup())
                .category(request.category())
                .build();
    }

    public ExerciseResponse toResponse(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getMuscleGroup(),
                exercise.getCategory()
        );
    }

    public void update(Exercise exercise, ExerciseRequest request) {
        exercise.setName(request.name());
        exercise.setDescription(request.description());
        exercise.setMuscleGroup(request.muscleGroup());
        exercise.setCategory(request.category());
    }
}