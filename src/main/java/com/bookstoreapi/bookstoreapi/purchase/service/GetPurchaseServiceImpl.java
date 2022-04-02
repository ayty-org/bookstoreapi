package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPurchaseServiceImpl implements GetPurchaseService{

    @Autowired
    private PurchaseService purchaseService;


    @Override
    public PurchaseDTO findById(Long id) {
        return new PurchaseDTO(purchaseService.findById(id));
    }
}
