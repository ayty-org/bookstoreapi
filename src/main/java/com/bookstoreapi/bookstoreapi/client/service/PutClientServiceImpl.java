package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PutClientServiceImpl implements PutClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client clientSaved = this.findById(id);
        BeanUtils.copyProperties(clientDTO, clientSaved);
        return new ClientDTO(clientRepository.save(clientSaved));
    }

    private Client findById(Long id){
        return clientRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("client with id "+id+" not found");
    });
    }
}
