package com.gfc.gymfactory.mappers;

import com.gfc.gymfactory.domain.entities.WorkoutExercise;
import com.gfc.gymfactory.dtos.request.WorkoutExerciseUpdateRequest;
import com.gfc.gymfactory.dtos.response.WorkoutExerciseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseMapper {

    private final ExerciseMapper exerciseMapper;

    public WorkoutExerciseResponse toResponse(WorkoutExercise workoutExercise) {
        return new WorkoutExerciseResponse(
                workoutExercise.getId(),
                workoutExercise.getSets(),
                workoutExercise.getReps(),
                workoutExercise.getRestSeconds(),
                workoutExercise.getWeight(),
                workoutExercise.getNotes(),
                exerciseMapper.toResponse(workoutExercise.getExercise())
        );
    }

    public void update(WorkoutExercise workoutExercise, WorkoutExerciseUpdateRequest request) {
        workoutExercise.setSets(request.sets());
        workoutExercise.setReps(request.reps());
        workoutExercise.setRestSeconds(request.restSeconds());
        workoutExercise.setWeight(request.weight());
        workoutExercise.setNotes(request.notes());
    }
}