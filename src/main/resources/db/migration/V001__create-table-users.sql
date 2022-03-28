CREATE SEQUENCE client_id_sequence INCREMENT 1;
CREATE TABLE clients(
    id BIGINT DEFAULT nextval('client_id_sequence') PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    age INT NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    email VARCHAR(40) NOT NULL,
    gender VARCHAR(10) NOT NULL
);