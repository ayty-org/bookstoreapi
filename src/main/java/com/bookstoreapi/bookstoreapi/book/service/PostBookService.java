package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookDTO;

@FunctionalInterface
public interface PostBookService {

    BookDTO save(BookDTO bookDTO);
}