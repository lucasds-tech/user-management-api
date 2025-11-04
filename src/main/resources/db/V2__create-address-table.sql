CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE address (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    cep VARCHAR(10) NOT NULL,
    street VARCHAR(100) NOT NULL,
    number VARCHAR(10) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);