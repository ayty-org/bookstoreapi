CREATE TABLE purchase_purchased_books(

    purchased_books_id BIGINT NOT NULL,
    purchase_id BIGINT NOT NULL,

    PRIMARY KEY (purchased_books_id, purchase_id),
    FOREIGN KEY (purchased_books_id) REFERENCES book,
    FOREIGN KEY (purchase_id) REFERENCES purchase
)