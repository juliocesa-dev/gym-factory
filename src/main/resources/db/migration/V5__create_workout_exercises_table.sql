CREATE TABLE workout_exercises (
                                   id BIGSERIAL PRIMARY KEY,
                                   workout_id BIGINT NOT NULL,
                                   exercise_id BIGINT NOT NULL,
                                   sets INTEGER NOT NULL,
                                   reps INTEGER NOT NULL,
                                   rest_seconds INTEGER,
                                   weight DOUBLE PRECISION,
                                   notes TEXT,
                                   active BOOLEAN NOT NULL,
                                   deleted BOOLEAN NOT NULL,
                                   created_at TIMESTAMP,
                                   updated_at TIMESTAMP,

                                   CONSTRAINT fk_workout_exercise_workout FOREIGN KEY (workout_id) REFERENCES workouts(id),
                                   CONSTRAINT fk_workout_exercise_exercise FOREIGN KEY (exercise_id) REFERENCES exercises(id)
);