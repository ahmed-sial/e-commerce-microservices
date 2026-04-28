CREATE TABLE IF NOT EXISTS category (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    name VARCHAR(50) UNIQUE NOT NULL,
    slug VARCHAR(50) UNIQUE NOT NULL
);
CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE TABLE IF NOT EXISTS product (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description VARCHAR(255) NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL,
    available_quantity INTEGER NOT NULL CHECK(available_quantity >= 0),
    price NUMERIC(38, 2) CHECK(price >= 0) NOT NULL,
    category_id INTEGER CONSTRAINT fk_cat_prod REFERENCES category(id)
);
