CREATE TABLE routines (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          status VARCHAR(20) NOT NULL,
                          is_template BOOLEAN NOT NULL,
                          start_date DATE,
                          end_date DATE,
                          instructor_id UUID NOT NULL,
                          student_id UUID,
                          active BOOLEAN NOT NULL,
                          deleted BOOLEAN NOT NULL,
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP,

                          CONSTRAINT fk_routine_instructor FOREIGN KEY (instructor_id) REFERENCES users(id),
                          CONSTRAINT fk_routine_student FOREIGN KEY (student_id) REFERENCES users(id)
);