package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.client.service.abstracts.FindByUuidClientAbstract;
import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl extends FindByUuidClientAbstract implements DeleteClientService {

    private final ClientRepository clientRepository;
    private final PurchaseRepository purchaseRepository;


    @Override
    public void delete(UUID id) throws EntityNotFoundException, DeleteException {
        if (purchaseRepository.existsByClientUuid(id)) {
            throw new DeleteException(id, Client.class.getSimpleName());
        }
        clientRepository.delete(this.findByUuid(id));
    }
}
