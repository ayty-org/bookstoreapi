CREATE TABLE books_categories(
    book_id uuid NOT NULL REFERENCES books(uuid),
    category_id BIGINT NOT NULL REFERENCES categories(id),

    PRIMARY KEY (book_id, category_id)
)