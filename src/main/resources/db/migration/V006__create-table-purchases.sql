CREATE SEQUENCE purchase_id_sequence INCREMENT 1;
CREATE TABLE purchase(
    id BIGINT DEFAULT nextval('purchase_id_sequence') PRIMARY KEY,
    client_id BIGINT NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    is_completed BOOLEAN NOT NULL,

    FOREIGN KEY(client_id) REFERENCES client
);