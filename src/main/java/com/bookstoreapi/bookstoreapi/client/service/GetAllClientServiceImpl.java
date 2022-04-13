package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetAllClientServiceImpl implements GetAllClientService {

    private final ClientRepository clientRepository;


    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientDTO::new).collect(Collectors.toList());
    }
}
