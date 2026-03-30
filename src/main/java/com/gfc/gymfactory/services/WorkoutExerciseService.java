package com.gfc.gymfactory.services;

import com.gfc.gymfactory.domain.entities.Exercise;
import com.gfc.gymfactory.domain.entities.Workout;
import com.gfc.gymfactory.domain.entities.WorkoutExercise;
import com.gfc.gymfactory.dtos.request.WorkoutExerciseRequest;
import com.gfc.gymfactory.dtos.request.WorkoutExerciseUpdateRequest;
import com.gfc.gymfactory.dtos.response.WorkoutExerciseResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.mappers.WorkoutExerciseMapper;
import com.gfc.gymfactory.repositories.ExerciseRepository;
import com.gfc.gymfactory.repositories.WorkoutExerciseRepository;
import com.gfc.gymfactory.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkoutExerciseService {

    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseMapper workoutExerciseMapper;

    @Transactional
    public WorkoutExerciseResponse create(WorkoutExerciseRequest request) {
        Workout workout = workoutRepository.findByIdOrThrow(request.workoutId(), "Treino não encontrado");

        Exercise exercise = exerciseRepository.findByIdOrThrow(request.exerciseId(), "Exercício não encontrado");

        WorkoutExercise workoutExercise = WorkoutExercise.builder()
                .workout(workout)
                .exercise(exercise)
                .sets(request.sets())
                .reps(request.reps())
                .restSeconds(request.restSeconds())
                .weight(request.weight())
                .notes(request.notes())
                .build();

        return workoutExerciseMapper.toResponse(workoutExerciseRepository.save(workoutExercise));
    }

    @Transactional(readOnly = true)
    public WorkoutExerciseResponse findById(Long id) {
        return workoutExerciseMapper.toResponse(workoutExerciseRepository.findByIdOrThrow(id, "Exercício do treino não encontrado"));
    }

    @Transactional(readOnly = true)
    public PageResponse<WorkoutExerciseResponse> findByWorkout(Long workoutId, Pageable pageable) {
        return PageResponse.of(workoutExerciseRepository.findAllByWorkoutId(workoutId, pageable)
                .map(workoutExerciseMapper::toResponse));
    }

    @Transactional
    public WorkoutExerciseResponse update(Long id, WorkoutExerciseUpdateRequest request) {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findByIdOrThrow(id, "Exercício do treino não encontrado");
        workoutExerciseMapper.update(workoutExercise, request);
        return workoutExerciseMapper.toResponse(workoutExerciseRepository.save(workoutExercise));
    }

    @Transactional
    public void delete(Long id) {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findByIdOrThrow(id, "Exercício do treino não encontrado");
        workoutExerciseRepository.delete(workoutExercise);
    }
}