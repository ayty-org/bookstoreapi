package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetClientServiceImpl implements GetClientService{

    private final ClientRepository clientRepository;


    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return clientRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException(id, ClientDTO.getClassName());
        });
    }
}
