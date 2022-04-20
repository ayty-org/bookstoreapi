package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.service.abstracts.FindByUuidClientAbstract;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetClientServiceImpl extends FindByUuidClientAbstract implements GetClientService{

    @Override
    public Client getByUuid(UUID id) throws EntityNotFoundException {
        return this.findByUuid(id);
    }
}
