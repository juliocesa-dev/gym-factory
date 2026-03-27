package com.gfc.gymfactory.services;

import com.gfc.gymfactory.domain.entities.RoutineRequest;
import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.domain.enums.RoutineRequestStatus;
import com.gfc.gymfactory.dtos.request.RoutineRequestCreateDto;
import com.gfc.gymfactory.dtos.request.RoutineRequestRejectDto;
import com.gfc.gymfactory.dtos.response.RoutineRequestCreateResponse;
import com.gfc.gymfactory.dtos.response.RoutineRequestRejectResponse;
import com.gfc.gymfactory.dtos.response.RoutineRequestResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.factories.RoutineRequestFactory;
import com.gfc.gymfactory.mappers.RoutineRequestMapper;
import com.gfc.gymfactory.repositories.RoutineRequestRepository;
import com.gfc.gymfactory.repositories.UserRepository;
import com.gfc.gymfactory.validators.RoutineValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoutineRequestService {

    private final RoutineRequestRepository routineRequestRepository;
    private final UserRepository userRepository;
    private final RoutineRequestMapper routineRequestMapper;
    private final RoutineRequestFactory routineRequestFactory;
    private final RoutineValidator routineValidator;

    @Transactional
    public RoutineRequestCreateResponse create(RoutineRequestCreateDto dto) {
        User student = userRepository.findByIdOrThrow(dto.studentId(), "Aluno não encontrado");
        routineValidator.throwIfNotStudent(student);

        RoutineRequest routineRequest = routineRequestFactory.create(dto, student);

        return routineRequestMapper.toCreateResponse(routineRequestRepository.save(routineRequest));
    }

    @Transactional(readOnly = true)
    public RoutineRequestResponse findById(Long id) {
        return routineRequestMapper.toResponse(routineRequestRepository.findByIdOrThrow(id, "Solicitação não encontrada"));
    }

    @Transactional(readOnly = true)
    public PageResponse<RoutineRequestResponse> findAll(RoutineRequestStatus status, Pageable pageable) {
        if (status != null) {
            return PageResponse.of(routineRequestRepository.findAllByStatus(status, pageable).map(routineRequestMapper::toResponse));
        }
        return PageResponse.of(routineRequestRepository.findAll(pageable).map(routineRequestMapper::toResponse));
    }

    @Transactional(readOnly = true)
    public PageResponse<RoutineRequestResponse> findByStudent(UUID studentId, RoutineRequestStatus status, Pageable pageable) {
        if (status != null) {
            return PageResponse.of(routineRequestRepository
                    .findAllByStudentIdAndStatus(studentId, status, pageable)
                            .map(routineRequestMapper::toResponse)
            );
        }
        return PageResponse.of(routineRequestRepository.findAllByStudentId(studentId, pageable).map(routineRequestMapper::toResponse));
    }

    @Transactional
    public RoutineRequestRejectResponse reject(Long id, RoutineRequestRejectDto dto) {
        RoutineRequest routineRequest = routineRequestRepository.findByIdOrThrow(id, "Solicitação não encontrada");
        routineValidator.throwIfAlreadyProcessed(routineRequest);

        routineRequest.setStatus(RoutineRequestStatus.REJECTED);
        routineRequest.setRejectionReason(dto.rejectionReason());

        return routineRequestMapper.toRejectResponse(routineRequestRepository.save(routineRequest));
    }
}