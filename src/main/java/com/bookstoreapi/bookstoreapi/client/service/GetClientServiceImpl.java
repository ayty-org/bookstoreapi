package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetClientServiceImpl implements GetClientService{

    private final ClientService clientService;


    @Override
    public ClientDTO findById(Long id){
        return ClientDTO.from(clientService.findById(id));
    }
}
