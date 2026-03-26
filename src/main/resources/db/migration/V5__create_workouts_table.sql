CREATE TABLE workouts (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          routine_id BIGINT NOT NULL,
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP,

                          CONSTRAINT fk_workout_routine FOREIGN KEY (routine_id) REFERENCES routines(id)
);