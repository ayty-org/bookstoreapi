package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletePurchaseServiceImpl implements DeletePurchaseService{

    private final PurchaseRepository purchaseRepository;
    private final PurchaseService purchaseService;


    @Override
    public void delete(Long id){
        purchaseRepository.delete(purchaseService.findById(id));
    }
}
