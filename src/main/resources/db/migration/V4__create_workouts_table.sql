CREATE TABLE workouts (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          is_template BOOLEAN NOT NULL,
                          routine_id BIGINT,
                          instructor_id UUID,
                          active BOOLEAN NOT NULL,
                          deleted BOOLEAN NOT NULL,
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP,

                          CONSTRAINT fk_workout_routine FOREIGN KEY (routine_id) REFERENCES routines(id),
                          CONSTRAINT fk_workout_instructor FOREIGN KEY (instructor_id) REFERENCES users(id)
);