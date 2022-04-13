package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetClientServiceImpl implements GetClientService{

    private final ClientService clientService;

    @Override
    public ClientDTO findById(Long id){
        return new ClientDTO(clientService.findById(id));
    }
}
