package com.gfc.gymfactory.services;

import com.gfc.gymfactory.domain.entities.Exercise;
import com.gfc.gymfactory.domain.enums.ExerciseCategory;
import com.gfc.gymfactory.domain.enums.MuscleGroup;
import com.gfc.gymfactory.dtos.request.ExerciseRequest;
import com.gfc.gymfactory.dtos.response.ExerciseResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.mappers.ExerciseMapper;
import com.gfc.gymfactory.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    @Transactional
    public ExerciseResponse create(ExerciseRequest request) {
        Exercise exercise = exerciseMapper.toEntity(request);
        return exerciseMapper.toResponse(exerciseRepository.save(exercise));
    }

    public ExerciseResponse findById(Long id) {
        return exerciseMapper.toResponse(
                exerciseRepository.findByIdOrThrow(id, "Exercício não encontrado")
        );
    }

    public PageResponse<ExerciseResponse> findAll(
            String name,
            MuscleGroup muscleGroup,
            ExerciseCategory category,
            Pageable pageable) {

        if (name != null && !name.isBlank()) {
            return PageResponse.of(
                    exerciseRepository.findAllByNameContaining(name, pageable)
                            .map(exerciseMapper::toResponse)
            );
        }
        if (muscleGroup != null && category != null) {
            return PageResponse.of(
                    exerciseRepository.findAllByMuscleGroupAndCategory(muscleGroup, category, pageable)
                            .map(exerciseMapper::toResponse)
            );
        }
        if (muscleGroup != null) {
            return PageResponse.of(
                    exerciseRepository.findAllByMuscleGroup(muscleGroup, pageable)
                            .map(exerciseMapper::toResponse)
            );
        }
        if (category != null) {
            return PageResponse.of(
                    exerciseRepository.findAllByCategory(category, pageable)
                            .map(exerciseMapper::toResponse)
            );
        }
        return PageResponse.of(
                exerciseRepository.findAll(pageable).map(exerciseMapper::toResponse)
        );
    }

    @Transactional
    public ExerciseResponse update(Long id, ExerciseRequest request) {
        Exercise exercise = exerciseRepository.findByIdOrThrow(id, "Exercício não encontrado");
        exerciseMapper.update(exercise, request);
        return exerciseMapper.toResponse(exerciseRepository.save(exercise));
    }

    @Transactional
    public void delete(Long id) {
        Exercise exercise = exerciseRepository.findByIdOrThrow(id, "Exercício não encontrado");
        exerciseRepository.delete(exercise);
    }
}