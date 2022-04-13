CREATE TABLE books_categories(
    book_id BIGINT NOT NULL REFERENCES books,
    categories_id BIGINT NOT NULL REFERENCES categories,

    PRIMARY KEY (book_id, categories_id)
)