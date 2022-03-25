CREATE SEQUENCE category_id_sequence INCREMENT 1;
CREATE TABLE categories(
    id BIGINT DEFAULT nextval('category_id_sequence') PRIMARY KEY,
    name VARCHAR(60) NOT NULL
);