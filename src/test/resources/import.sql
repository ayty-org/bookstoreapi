DROP TABLE books_categories;

INSERT INTO categories (name) VALUES('Romance');
INSERT INTO categories (name) VALUES('Action');
INSERT INTO categories (name) VALUES('Fantasy');

INSERT INTO clients (name, age, telephone, email, gender) VALUES ('Jenipapo', 19, '83996438691','jenipapo@coldmail.com','Male');
INSERT INTO clients (name, age, telephone, email, gender) VALUES ('Ana', 46,  '83996438691', 'ana@coldmail.com','Female');
INSERT INTO clients (name, age, telephone, email, gender) VALUES ('Patricia', 25, '83996438691','patricia@coldmail.com', 'Trans');
INSERT INTO clients (name, age, telephone, email, gender) VALUES ('Patricia', 25, '83996438691','patricia@coldmail.com', 'Trans');


INSERT INTO books (title, synopsis, isbn, publication_year, price, quantity_in_stock, author_name) VALUES ('JavaScript', 'Aprenda JavaScript', '9788533302273', '2001-03-14', 50.00, 23, 'JN Papo');
INSERT INTO books (title, synopsis, isbn, publication_year, price, quantity_in_stock, author_name) VALUES ('JavaScript', 'Aprenda a primeira versão do Angular', '9788533302273', '2000-04-15', 80.00, 4, 'Gu Gou');
INSERT INTO books (title, synopsis, isbn, publication_year, price, quantity_in_stock, author_name) VALUES ('Algoritmos', 'Entenda lógica de programação', '9788533302273', '2000-04-30', 100.00, 23, 'JN Papo');

CREATE TABLE books_categories(book_id BIGINT NOT NULL REFERENCES books, categories_id BIGINT NOT NULL REFERENCES categories,PRIMARY KEY (book_id, categories_id));

INSERT INTO books_categories (book_id, categories_id) VALUES (1,1);
INSERT INTO books_categories (book_id, categories_id) VALUES (1,2);
INSERT INTO books_categories (book_id, categories_id) VALUES (1,3);
INSERT INTO books_categories (book_id, categories_id) VALUES (2,1);
INSERT INTO books_categories (book_id, categories_id) VALUES (2,2);
INSERT INTO books_categories (book_id, categories_id) VALUES (2,3);
INSERT INTO books_categories (book_id, categories_id) VALUES (3,1);
INSERT INTO books_categories (book_id, categories_id) VALUES (3,2);
INSERT INTO books_categories (book_id, categories_id) VALUES (3,3);