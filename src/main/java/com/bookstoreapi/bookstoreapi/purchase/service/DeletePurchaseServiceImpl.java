package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePurchaseServiceImpl implements DeletePurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseService purchaseService;


    @Override
    public void delete(Long id){
        purchaseRepository.delete(purchaseService.findById(id));
    }
}
