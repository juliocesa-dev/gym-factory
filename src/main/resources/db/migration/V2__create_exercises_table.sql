CREATE TABLE exercises (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           description TEXT,
                           muscle_group VARCHAR(30) NOT NULL,
                           category VARCHAR(30) NOT NULL,
                           created_at TIMESTAMP,
                           updated_at TIMESTAMP
);