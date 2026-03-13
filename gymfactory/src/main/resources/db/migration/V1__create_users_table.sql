CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    email VARCHAR(120) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50),
    active BOOLEAN NOT NULL,
    deleted BOOLEAN NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

INSERT INTO users (
    id,
    name,
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
    'admin@gymfactory.com',
    '$2a$10$EXAMPLEHASHEDPASSWORD',
    'ADMIN',
    true,
    false,
    NOW(),
    NOW()
);
