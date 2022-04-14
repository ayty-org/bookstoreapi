package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;

import java.util.List;

@FunctionalInterface
public interface SaveBookService {

    Book save(Book book, List<Long> categories);
}
