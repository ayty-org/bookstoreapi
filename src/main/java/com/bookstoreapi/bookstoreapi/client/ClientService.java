package com.bookstoreapi.bookstoreapi.client;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientFieldsVerifier clientFieldsVerification;


    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(Long id){
        return clientRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("client with id "+id+" not found");
        });
    }

    public Client save(Client client){
        clientFieldsVerification.clientFieldsVerification(client);
        return clientRepository.save(client);
    }

    public void delete(Long id){
        Client clientSaved = this.findById(id);
        clientRepository.delete(clientSaved);
    }

    public Client update(Long id, Client client){
        Client clientSaved = this.findById(id);
        BeanUtils.copyProperties(client, clientSaved, "id");
        return this.save(clientSaved);
    }
}
