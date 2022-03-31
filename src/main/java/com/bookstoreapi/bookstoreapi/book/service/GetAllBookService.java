package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookDTO;

import java.util.List;

@FunctionalInterface
public interface GetAllBookService {

    List<BookDTO> findAll();
}
