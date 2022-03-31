package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetClientServiceImpl implements GetClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("client with id "+id+" not found");
        });
        return new ClientDTO(client);
    }
}
