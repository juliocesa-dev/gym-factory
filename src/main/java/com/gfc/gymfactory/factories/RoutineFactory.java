package com.gfc.gymfactory.factories;

import com.gfc.gymfactory.domain.entities.Routine;
import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.domain.entities.Workout;
import com.gfc.gymfactory.domain.entities.WorkoutExercise;
import org.springframework.stereotype.Component;

@Component
public class RoutineFactory {

    public Routine cloneFromTemplate(Routine template, User student, User instructor) {
        Routine clone = Routine.builder()
                .name(template.getName())
                .description(template.getDescription())
                .status(template.getStatus())
                .goal(template.getGoal())
                .difficulty(template.getDifficulty())
                .isTemplate(false)
                .startDate(template.getStartDate())
                .endDate(template.getEndDate())
                .instructor(instructor)
                .student(student)
                .build();

        for (Workout templateWorkout : template.getWorkouts()) {
            Workout clonedWorkout = Workout.builder()
                    .name(templateWorkout.getName())
                    .description(templateWorkout.getDescription())
                    .isTemplate(false)
                    .routine(clone)
                    .build();

            for (WorkoutExercise templateExercise : templateWorkout.getExercises()) {
                WorkoutExercise clonedExercise = WorkoutExercise.builder()
                        .workout(clonedWorkout)
                        .exercise(templateExercise.getExercise())
                        .sets(templateExercise.getSets())
                        .reps(templateExercise.getReps())
                        .restSeconds(templateExercise.getRestSeconds())
                        .weight(templateExercise.getWeight())
                        .notes(templateExercise.getNotes())
                        .build();

                clonedWorkout.getExercises().add(clonedExercise);
            }

            clone.getWorkouts().add(clonedWorkout);
        }

        return clone;
    }
}
