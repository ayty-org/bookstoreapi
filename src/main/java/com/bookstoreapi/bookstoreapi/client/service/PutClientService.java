package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientDTO;

@FunctionalInterface
public interface PutClientService {

    ClientDTO update(Long id, ClientDTO clientDTO);
}
