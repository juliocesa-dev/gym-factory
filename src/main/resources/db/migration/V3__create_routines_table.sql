CREATE TABLE routines (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          status VARCHAR(30) NOT NULL,
                          goal VARCHAR(30) NOT NULL,
                          difficulty VARCHAR(30) NOT NULL,
                          is_template BOOLEAN NOT NULL,
                          start_date DATE,
                          end_date DATE,
                          instructor_id UUID NOT NULL,
                          student_id UUID,
                          active BOOLEAN NOT NULL,
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP,

                          CONSTRAINT fk_routine_instructor FOREIGN KEY (instructor_id) REFERENCES users(id),
                          CONSTRAINT fk_routine_student FOREIGN KEY (student_id) REFERENCES users(id)
);

INSERT INTO routines (
    name,
    description,
    status,
    goal,
    difficulty,
    is_template,
    start_date,
    end_date,
    instructor_id,
    student_id,
    active,
    created_at
) VALUES (
             'Rotina de Hipertrofia A',
             'Rotina focada em ganho de massa muscular',
             'DONE',
             'HYPERTROPHY',
             'INTERMEDIATE',
             false,
             '2026-03-24',
             '2026-06-24',
             '550e8400-e29b-41d4-a716-446655440001',
             '550e8400-e29b-41d4-a716-446655440002',
             true,
             NOW()
         );

INSERT INTO routines (
    name,
    description,
    status,
    goal,
    difficulty,
    is_template,
    start_date,
    end_date,
    instructor_id,
    student_id,
    active,
    created_at
) VALUES (
             'Template - Hipertrofia Full Body',
             'Template de rotina completa para ganho de massa muscular',
             'DONE',
             'HYPERTROPHY',
             'INTERMEDIATE',
             true,
             NULL,
             NULL,
             '550e8400-e29b-41d4-a716-446655440001',
             NULL,
             true,
             NOW()
         );