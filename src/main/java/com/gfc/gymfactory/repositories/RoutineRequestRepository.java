package com.gfc.gymfactory.repositories;

import com.gfc.gymfactory.domain.entities.RoutineRequest;
import com.gfc.gymfactory.domain.enums.RoutineRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoutineRequestRepository extends BaseRepository<RoutineRequest, Long> {

    Page<RoutineRequest> findAllByStatus(RoutineRequestStatus status, Pageable pageable);

    Page<RoutineRequest> findAllByStudentId(UUID studentId, Pageable pageable);

    Page<RoutineRequest> findAllByStudentIdAndStatus(
            UUID studentId,
            RoutineRequestStatus status,
            Pageable pageable
    );
}