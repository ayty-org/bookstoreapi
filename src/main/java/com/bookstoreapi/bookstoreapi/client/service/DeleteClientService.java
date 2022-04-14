package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;

@FunctionalInterface
public interface DeleteClientService {

    void delete(Long id) throws EntityNotFoundException;
}
