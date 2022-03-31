package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class GetAllClientServiceImpl implements GetAllClientService{

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public List<ClientDTO> findAll(){
        return clientRepository.findAll()
                .stream()
                .map(ClientDTO::new).collect(Collectors.toList());
    }
}
