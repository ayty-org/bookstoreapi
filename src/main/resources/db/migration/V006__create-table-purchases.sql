CREATE TABLE purchases(
    id SERIAL8 PRIMARY KEY,
    client_id BIGINT NOT NULL REFERENCES clients(id),
    amount DOUBLE PRECISION NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    is_completed BOOLEAN NOT NULL

);