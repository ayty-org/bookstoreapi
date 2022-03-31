package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;


    public boolean existsByBookId(Long id){
        return purchaseRepository.existsByPurchasedBooksId(id);
    }

    public boolean existsByClientId(Long id){
        return purchaseRepository.existsByClientId(id);
    }
}
