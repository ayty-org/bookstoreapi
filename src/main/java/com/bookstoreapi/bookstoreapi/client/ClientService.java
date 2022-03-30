package com.bookstoreapi.bookstoreapi.client;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public List<ClientDTO> findAll(){
        return clientRepository.findAll()
                .stream()
                .map(ClientDTO::new).collect(Collectors.toList());
    }

    private Client findById(Long id){
        return clientRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("client with id "+id+" not found");
        });
    }

    public ClientDTO getDTO(Long id){
        return new ClientDTO(this.findById(id));
    }

    public ClientDTO save(ClientDTO clientDTO){
        clientRepository.save(new Client(clientDTO));
        return clientDTO;
    }

    public void delete(Long id){
        clientRepository.delete(this.findById(id));
    }

    public ClientDTO update(Long id, ClientDTO clientDTO){
        Client clientSaved = this.findById(id);
        BeanUtils.copyProperties(clientDTO, clientSaved);
        return new ClientDTO(clientRepository.save(clientSaved));
    }
}
