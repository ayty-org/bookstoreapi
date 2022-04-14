package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService{

    private final ClientRepository clientRepository;
    private final PurchaseRepository purchaseRepository;



    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(clientRepository.existsById(id)){
            if(purchaseRepository.existsByClientId(id)){
                throw new DeleteException(id, ClientDTO.getClassName());
            }
            clientRepository.delete(clientRepository.getById(id));
        }else{
            throw new EntityNotFoundException(id, ClientDTO.getClassName());
        }
    }
}
