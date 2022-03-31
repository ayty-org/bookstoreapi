package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DeleteClientServiceImpl implements DeleteClientService{

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public void delete(Long id){
        clientRepository.delete(this.findById(id));
    }

    private Client findById(Long id){
        return clientRepository.findById(id).orElseThrow(()->{
            throw new EntityNotFoundException("client with id "+id+" not found");
        });
    }
}
