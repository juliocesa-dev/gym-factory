package com.gfc.gymfactory.services;

import com.gfc.gymfactory.domain.entities.Routine;
import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.domain.enums.RoutineDifficulty;
import com.gfc.gymfactory.dtos.request.RoutineRequest;
import com.gfc.gymfactory.dtos.request.RoutineUpdateRequest;
import com.gfc.gymfactory.dtos.response.RoutineResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.exception.ApiException;
import com.gfc.gymfactory.factories.RoutineFactory;
import com.gfc.gymfactory.mappers.RoutineMapper;
import com.gfc.gymfactory.repositories.RoutineRepository;
import com.gfc.gymfactory.repositories.UserRepository;
import com.gfc.gymfactory.validators.RoutineValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;
    private final RoutineMapper routineMapper;
    private final RoutineValidator routineValidator;
    private final RoutineFactory routineFactory;

    @Transactional
    public RoutineResponse create(RoutineRequest request) {
        routineValidator.validate(request);

        User instructor = userRepository.findByIdOrThrow(request.instructorId(), "Instrutor não encontrado");
        routineValidator.throwIfNotInstructor(instructor);

        Routine routine = routineMapper.toEntity(request);
        routine.setInstructor(instructor);

        if (request.studentId() != null) {
            User student = userRepository.findByIdOrThrow(request.studentId(), "Aluno não encontrado");
            routineValidator.throwIfNotStudent(student);
            routine.setStudent(student);
        }

        return routineMapper.toResponse(routineRepository.save(routine));
    }

    @Transactional(readOnly = true)
    public RoutineResponse findById(Long id) {
        return routineMapper.toResponse(
                routineRepository.findByIdOrThrow(id, "Rotina não encontrada")
        );
    }

    @Transactional(readOnly = true)
    public PageResponse<RoutineResponse> findAll(Pageable pageable) {
        return PageResponse.of(routineRepository.findAll(pageable).map(routineMapper::toResponse));
    }

    @Transactional(readOnly = true)
    public PageResponse<RoutineResponse> findByStudent(UUID studentId, Pageable pageable) {
        return PageResponse.of(routineRepository.findAllByStudentId(studentId, pageable).map(routineMapper::toResponse));
    }

    @Transactional(readOnly = true)
    public PageResponse<RoutineResponse> findTemplates(RoutineDifficulty difficulty, Pageable pageable) {
        return PageResponse.of(routineRepository.findAllTemplates(difficulty, pageable).map(routineMapper::toResponse));
    }

    @Transactional(readOnly = true)
    public PageResponse<RoutineResponse> findTemplatesByInstructor(UUID instructorId, RoutineDifficulty difficulty, Pageable pageable) {
        return PageResponse.of(routineRepository.findTemplatesByInstructorId(instructorId, difficulty, pageable).map(routineMapper::toResponse));
    }

    @Transactional
    public RoutineResponse update(Long id, RoutineUpdateRequest request) {
        routineValidator.validateUpdate(request);
        Routine routine = routineRepository.findByIdOrThrow(id, "Rotina não encontrada");
        routineMapper.update(routine, request);
        return routineMapper.toResponse(routineRepository.save(routine));
    }

    @Transactional
    public void delete(Long id) {
        Routine routine = routineRepository.findByIdOrThrow(id, "Rotina não encontrada");
        routineRepository.delete(routine);
    }

    @Transactional
    public RoutineResponse applyTemplate(Long templateId, UUID studentId, UUID instructorId) {
        Routine template = routineRepository.findByIdOrThrow(templateId, "Template não encontrado");

        if (!template.getIsTemplate()) {
            throw new ApiException("Rotina não é um template", HttpStatus.BAD_REQUEST);
        }

        User student = userRepository.findByIdOrThrow(studentId, "Aluno não encontrado");
        routineValidator.throwIfNotStudent(student);

        User instructor = userRepository.findByIdOrThrow(instructorId, "Instrutor não encontrado");
        routineValidator.throwIfNotInstructor(instructor);

        Routine clone = routineFactory.cloneFromTemplate(template, student, instructor);

        return routineMapper.toResponse(routineRepository.save(clone));
    }
}