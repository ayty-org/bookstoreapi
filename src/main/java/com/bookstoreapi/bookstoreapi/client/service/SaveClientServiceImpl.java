package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveClientServiceImpl implements SaveClientService {

    private final ClientRepository clientRepository;


    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
