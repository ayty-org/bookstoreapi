package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DeleteClientServiceImpl implements DeleteClientService{

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PurchaseService purchaseService;


    @Override
    public void delete(Long id){
        if(purchaseService.existsByBookId(id)){
            throw new DataIntegrityViolationException(
                    "One or more purchases have this client, it is not possible to delete");
        }
        clientRepository.delete(this.findById(id));
    }

    private Client findById(Long id){
        return clientRepository.findById(id).orElseThrow(()->{
            throw new EntityNotFoundException("client with id "+id+" not found");
        });
    }
}
