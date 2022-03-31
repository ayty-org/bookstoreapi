package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostClientServiceImpl implements PostClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        clientRepository.save(new Client(clientDTO));
        return clientDTO;

    }
}
