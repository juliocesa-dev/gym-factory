CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(120) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50),

    active BOOLEAN NOT NULL,
    deleted BOOLEAN NOT NULL,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP);

    INSERT INTO users (
        id,
        name,
        phone_number,
        email,
        password,
        role,
        active,
        deleted,
        created_at,
        updated_at
    ) VALUES (
        '550e8400-e29b-41d4-a716-446655440000',
        'Admin',
        '81993952117',
        'admin@gymfactory.com',
        '$2a$10$oBlBh4rNuviFGU5Vh4LiVOYy2adubHCySEj0qyuVGY/nOmDCSOlia',
        'ADMIN',
        true,
        false,
        NOW(),
        NOW()
    );

        INSERT INTO users (
            id,
            name,
            phone_number,
            email,
            password,
            role,
            active,
            deleted,
            created_at,
            updated_at
        ) VALUES (
            '550e8400-e29b-41d4-a716-446655440001',
            'Luiz',
            '81993952119',
            'luiz@gymfactory.com',
            '$2a$10$EXAMPLEHASHEDPASSWORD',
            'STUDENT',
            true,
            false,
            NOW(),
            NOW()
        );