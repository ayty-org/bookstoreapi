package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.exception.CategoryNotFoundException;

@FunctionalInterface
public interface SaveBookService {

    Book save(Book book) throws CategoryNotFoundException;
}
