package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientDTO;

import java.util.List;

@FunctionalInterface
public interface GetAllClientService {

    List<ClientDTO> findAll();

}
