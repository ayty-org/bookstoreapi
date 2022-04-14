package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;

@FunctionalInterface
public interface SaveBookService {

    Book save(Book book);
}
