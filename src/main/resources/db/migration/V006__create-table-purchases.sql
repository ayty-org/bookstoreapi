CREATE TABLE purchase(
    id SERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL REFERENCES client(id),
    amount DOUBLE PRECISION NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    is_completed BOOLEAN NOT NULL

);