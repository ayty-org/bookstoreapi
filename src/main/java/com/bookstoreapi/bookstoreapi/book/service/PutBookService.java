package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookDTO;

@FunctionalInterface
public interface PutBookService {

    BookDTO update(Long id, BookDTO bookDTO);
}
