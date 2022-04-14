package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService{

    private final ClientRepository clientRepository;


    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(clientRepository.existsById(id)){
            try{
                clientRepository.delete(clientRepository.getById(id));
            }catch (Exception e){
                throw new DeleteException(id, ClientDTO.getClassName());
            }
        }else{
            throw new EntityNotFoundException(id, ClientDTO.getClassName());
        }
    }



}
