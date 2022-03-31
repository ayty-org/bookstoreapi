package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseResumedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class GetPurchaseServiceImpl implements GetPurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;


    @Override
    public PurchaseResumedDTO findById(Long id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(() ->{
            throw new EntityNotFoundException("purchase with id "+id+" not found");
        });
        return new PurchaseResumedDTO(new PurchaseDTO(purchase));
    }
}
