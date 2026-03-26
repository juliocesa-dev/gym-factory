package com.gfc.gymfactory.services;

import com.gfc.gymfactory.domain.entities.Routine;
import com.gfc.gymfactory.domain.entities.Workout;
import com.gfc.gymfactory.dtos.request.WorkoutRequest;
import com.gfc.gymfactory.dtos.request.WorkoutUpdateRequest;
import com.gfc.gymfactory.dtos.response.WorkoutResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.mappers.WorkoutMapper;
import com.gfc.gymfactory.repositories.RoutineRepository;
import com.gfc.gymfactory.repositories.WorkoutRepository;
import com.gfc.gymfactory.validators.WorkoutValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final RoutineRepository routineRepository;
    private final WorkoutMapper workoutMapper;
    private final WorkoutValidator workoutValidator;

    @Transactional
    public WorkoutResponse create(WorkoutRequest request) {
        Routine routine = routineRepository.findByIdOrThrow(
                request.routineId(), "Rotina não encontrada"
        );

        workoutValidator.throwIfRoutineIsTemplate(routine);

        Workout workout = workoutMapper.toEntity(request);
        workout.setRoutine(routine);

        return workoutMapper.toResponse(workoutRepository.save(workout));
    }

    @Transactional(readOnly = true)
    public WorkoutResponse findById(Long id) {
        return workoutMapper.toResponse(
                workoutRepository.findByIdOrThrow(id, "Treino não encontrado")
        );
    }

    @Transactional(readOnly = true)
    public PageResponse<WorkoutResponse> findByRoutine(Long routineId, Pageable pageable) {
        return PageResponse.of(
                workoutRepository.findAllByRoutineId(routineId, pageable)
                        .map(workoutMapper::toResponse)
        );
    }

    @Transactional
    public WorkoutResponse update(Long id, WorkoutUpdateRequest request) {
        Workout workout = workoutRepository.findByIdOrThrow(id, "Treino não encontrado");
        workoutMapper.update(workout, request);
        return workoutMapper.toResponse(workoutRepository.save(workout));
    }

    @Transactional
    public void delete(Long id) {
        Workout workout = workoutRepository.findByIdOrThrow(id, "Treino não encontrado");
        workoutRepository.delete(workout);
    }
}