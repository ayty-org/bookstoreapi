package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostClientServiceImpl implements PostClientService {

    private final ClientRepository clientRepository;


    @Override
    public ClientDTO save(Client client) {
        return ClientDTO.from(clientRepository.save(client));
    }
}
