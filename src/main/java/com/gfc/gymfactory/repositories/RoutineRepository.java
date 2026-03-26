package com.gfc.gymfactory.repositories;

import com.gfc.gymfactory.domain.entities.Routine;
import com.gfc.gymfactory.domain.enums.RoutineDifficulty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoutineRepository extends BaseRepository<Routine, Long> {

    Page<Routine> findAllByStudentId(UUID studentId, Pageable pageable);

    @Query("SELECT r FROM Routine r WHERE r.isTemplate = true AND (:difficulty IS NULL OR r.difficulty = :difficulty)")
    Page<Routine> findAllTemplates(@Param("difficulty") RoutineDifficulty difficulty, Pageable pageable);

    @Query("SELECT r FROM Routine r WHERE r.isTemplate = true AND r.instructor.id = :instructorId AND (:difficulty IS NULL OR r.difficulty = :difficulty)")
    Page<Routine> findTemplatesByInstructorId(@Param("instructorId") UUID instructorId, @Param("difficulty") RoutineDifficulty difficulty, Pageable pageable);
}