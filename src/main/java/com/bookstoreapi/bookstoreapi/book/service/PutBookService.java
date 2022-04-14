package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;

import java.util.List;

@FunctionalInterface
public interface PutBookService {

    Book update(Long id, Book book, List<Long> categories);
}
