package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface GetBookService {

    Book getByUuid(UUID id) throws EntityNotFoundException;
}
