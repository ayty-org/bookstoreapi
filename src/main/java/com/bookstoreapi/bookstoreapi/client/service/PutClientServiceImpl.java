package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PutClientServiceImpl implements PutClientService{

    private final ClientRepository clientRepository;
    private final ClientService clientService;

    @Override
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client clientSaved = clientService.findById(id);
        BeanUtils.copyProperties(clientDTO, clientSaved);
        return new ClientDTO(clientRepository.save(clientSaved));
    }

}
