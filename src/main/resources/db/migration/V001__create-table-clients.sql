CREATE TABLE clients(
    id SERIAL8 PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    age INT NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    email VARCHAR(40) NOT NULL,
    gender VARCHAR(15) NOT NULL
);