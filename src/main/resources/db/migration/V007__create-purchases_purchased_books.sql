CREATE TABLE purchases_purchased_books(
    id SERIAL8 PRIMARY KEY,

    purchased_books_id BIGINT NOT NULL REFERENCES books,
    purchase_id BIGINT NOT NULL REFERENCES purchases
)