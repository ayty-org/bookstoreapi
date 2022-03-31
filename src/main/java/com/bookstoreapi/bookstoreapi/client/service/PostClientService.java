package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientDTO;

@FunctionalInterface
public interface PostClientService {

    ClientDTO save(ClientDTO clientDTO);
}
