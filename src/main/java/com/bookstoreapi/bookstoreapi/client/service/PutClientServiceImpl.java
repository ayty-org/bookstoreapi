package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PutClientServiceImpl implements PutClientService{

    private final ClientRepository clientRepository;

    @Override
    public Client update(Long id, Client client) throws EntityNotFoundException {
        if(clientRepository.existsById(id)){
            client.setId(id);
            return clientRepository.save(client);
        }
      throw new EntityNotFoundException(id, ClientDTO.getClassName());
    }
}
