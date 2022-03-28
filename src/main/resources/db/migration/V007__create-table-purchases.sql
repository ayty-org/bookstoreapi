CREATE SEQUENCE purchase_id_sequence INCREMENT 1;
CREATE TABLE purchases(
    id BIGINT DEFAULT nextval('purchase_id_sequence') PRIMARY KEY,
    client_id BIGINT NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    status VARCHAR(10) NOT NULL,

    FOREIGN KEY(client_id) REFERENCES clients
);