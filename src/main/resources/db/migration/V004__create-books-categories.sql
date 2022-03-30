CREATE TABLE book_categories(
    book_id BIGINT NOT NULL,
    categories_id BIGINT NOT NULL,

    PRIMARY KEY (book_id, categories_id),
    FOREIGN KEY (book_id) REFERENCES book,
    FOREIGN KEY (categories_id) REFERENCES category
)