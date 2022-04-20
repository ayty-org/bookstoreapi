package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.exception.CategoryNotFoundException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface UpdateBookService {

    Book update(UUID id, Book book) throws EntityNotFoundException, CategoryNotFoundException;
}
