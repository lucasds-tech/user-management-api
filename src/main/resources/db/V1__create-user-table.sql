CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE user (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    name VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    email VARCHAR(60) NOT NULL,
    password VARCHAR(30) NOT NULL,
);