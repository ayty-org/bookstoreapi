CREATE TABLE books(
    id SERIAL8 PRIMARY KEY,
    title VARCHAR(60) NOT NULL,
    synopsis VARCHAR(500) NOT NULL,
    isbn VARCHAR(50) NOT NULL,
    publication_year TIMESTAMP NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    quantity_in_stock INTEGER NOT NULL,
    author_name VARCHAR(60) NOT NULL
);