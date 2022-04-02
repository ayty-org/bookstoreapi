package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {


    @Autowired
    private ClientRepository clientRepository;

    public Client findById(Long id){
        return clientRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("client with id "+id+" not found");
        });
    }
}
