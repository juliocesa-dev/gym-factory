CREATE TABLE password_reset_tokens (
    id          BIGSERIAL PRIMARY KEY,
    email       VARCHAR(120) NOT NULL,
    code        VARCHAR(6)   NOT NULL,
    expires_at  TIMESTAMP    NOT NULL,
    used        BOOLEAN      NOT NULL,
    created_at  TIMESTAMP    NOT NULL
);
