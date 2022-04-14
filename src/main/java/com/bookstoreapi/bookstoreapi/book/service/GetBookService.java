package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;

@FunctionalInterface
public interface GetBookService {

    Book findById(Long id);
}
