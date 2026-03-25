package com.gfc.gymfactory.repositories;

import com.gfc.gymfactory.domain.entities.Exercise;
import com.gfc.gymfactory.domain.enums.ExerciseCategory;
import com.gfc.gymfactory.domain.enums.MuscleGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends BaseRepository<Exercise, Long> {

    Page<Exercise> findAllByMuscleGroup(MuscleGroup muscleGroup, Pageable pageable);

    Page<Exercise> findAllByCategory(ExerciseCategory category, Pageable pageable);

    Page<Exercise> findAllByMuscleGroupAndCategory(MuscleGroup muscleGroup, ExerciseCategory category, Pageable pageable);

//    @Query("SELECT e FROM Exercise e WHERE unaccent(LOWER(e.name)) LIKE unaccent(LOWER(CONCAT('%', :name, '%')))") - comentado temporariamente
    @Query("SELECT e FROM Exercise e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Exercise> findAllByNameContaining(@Param("name") String name, Pageable pageable);
}