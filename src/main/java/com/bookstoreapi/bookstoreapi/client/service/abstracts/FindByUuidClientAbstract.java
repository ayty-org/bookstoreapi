package com.bookstoreapi.bookstoreapi.client.service.abstracts;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class FindByUuidClientAbstract {

    private ClientRepository clientRepository;

    public Client findByUuid(UUID uuid) throws EntityNotFoundException{
        return clientRepository.findByUuid(uuid).orElseThrow(
                () -> new EntityNotFoundException(uuid, Client.class.getSimpleName())
        );
    }
}
