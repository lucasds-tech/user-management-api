CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE address (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    cep VARCHAR(10) NOT NULL,
    street VARCHAR(100) NOT NULL,
    number VARCHAR(10) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    users_id UUID NOT NULL,
    CONSTRAINT fk_address_users
        FOREIGN KEY (users_id)
            REFERENCES users(id)
            ON DELETE CASCADE
);

CREATE INDEX idx_address_users_id ON address(users_id);
CREATE INDEX idx_users_email ON users(email);