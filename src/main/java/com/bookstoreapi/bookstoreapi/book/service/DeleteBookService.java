package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface DeleteBookService {

    void delete(UUID id) throws EntityNotFoundException, DeleteException;
}
