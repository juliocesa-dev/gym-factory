package com.gfc.gymfactory.repositories;

import com.gfc.gymfactory.domain.entities.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends BaseRepository<Workout, Long> {

    Page<Workout> findAllByRoutineId(Long routineId, Pageable pageable);
}