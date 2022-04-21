package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import com.bookstoreapi.bookstoreapi.purchase.service.abstracts.FindByUuidPurchaseAbstract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@RequiredArgsConstructor
@Service
public class GetPurchaseServiceImpl extends FindByUuidPurchaseAbstract implements GetPurchaseService{

    private final PurchaseRepository purchaseRepository;


    public Purchase getByUuid(UUID id) throws EntityNotFoundException{
        return this.findByUuid(id);
    }
}
