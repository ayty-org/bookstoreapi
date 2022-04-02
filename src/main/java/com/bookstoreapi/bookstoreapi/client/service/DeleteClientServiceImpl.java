package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DeleteClientServiceImpl implements DeleteClientService{

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private ClientService clientService;


    @Override
    public void delete(Long id){
        if(purchaseService.existsByClientId(id)){
            throw new DataIntegrityViolationException
                    ("One or more purchases have this client, it is not possible to delete");
        }
        clientRepository.delete(clientService.findById(id));
    }
}
