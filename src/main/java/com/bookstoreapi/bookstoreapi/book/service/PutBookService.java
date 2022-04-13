package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;

@FunctionalInterface
public interface PutBookService {

    BookDTO update(Long id, Book book);
}
