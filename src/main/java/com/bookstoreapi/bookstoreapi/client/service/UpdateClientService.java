package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;

@FunctionalInterface
public interface UpdateClientService {

    Client update(Long id, Client client);
}
