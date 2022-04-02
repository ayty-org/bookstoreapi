CREATE SEQUENCE ppb_id_sequence INCREMENT 1;

CREATE TABLE purchase_purchased_books(
    id BIGINT DEFAULT nextval('ppb_id_sequence') PRIMARY KEY,

    purchased_books_id BIGINT NOT NULL,
    purchase_id BIGINT NOT NULL,
    FOREIGN KEY (purchased_books_id) REFERENCES book,
    FOREIGN KEY (purchase_id) REFERENCES purchase
)