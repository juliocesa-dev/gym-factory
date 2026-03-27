CREATE TABLE routine_requests (
                                  id BIGSERIAL PRIMARY KEY,
                                  student_id UUID NOT NULL,
                                  goal VARCHAR(30) NOT NULL,
                                  difficulty VARCHAR(30) NOT NULL,
                                  observations TEXT,
                                  status VARCHAR(20) NOT NULL,
                                  rejection_reason TEXT,
                                  routine_id BIGINT,
                                  created_at TIMESTAMP,
                                  updated_at TIMESTAMP,

                                  CONSTRAINT fk_routine_request_student FOREIGN KEY (student_id) REFERENCES users(id),
                                  CONSTRAINT fk_routine_request_routine FOREIGN KEY (routine_id) REFERENCES routines(id)
);