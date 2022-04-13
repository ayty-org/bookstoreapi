package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService{

    private final ClientRepository clientRepository;
    private final PurchaseService purchaseService;
    private final ClientService clientService;


    @Override
    public void delete(Long id){
        if(purchaseService.existsByClientId(id)){
            throw new DataIntegrityViolationException
                    ("One or more purchases have this client, it is not possible to delete");
        }
        clientRepository.delete(clientService.findById(id));
    }
}
