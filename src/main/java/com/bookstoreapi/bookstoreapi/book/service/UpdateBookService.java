package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;

@FunctionalInterface
public interface UpdateBookService {

    Book update(Long id, Book book);
}
