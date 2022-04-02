package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetClientServiceImpl implements GetClientService{

    @Autowired
    private ClientService clientService;

    @Override
    public ClientDTO findById(Long id){
        return new ClientDTO(clientService.findById(id));
    }
}
