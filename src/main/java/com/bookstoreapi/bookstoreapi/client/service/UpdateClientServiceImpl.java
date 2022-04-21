package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.client.service.abstracts.FindByUuidClientAbstract;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateClientServiceImpl extends FindByUuidClientAbstract implements UpdateClientService {

    private final ClientRepository clientRepository;


    @Override
    public Client update(UUID id, Client client) throws EntityNotFoundException {
        Client clientSaved = this.findByUuid(id);
        client.setId(clientSaved.getId());
        client.setUuid(id);
        return clientRepository.save(client);
    }
}
