package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetPurchaseServiceImpl implements GetPurchaseService{

    private final PurchaseService purchaseService;


    @Override
    public PurchaseDTO findById(Long id) {
        return  PurchaseDTO.from(purchaseService.findById(id));
    }
}
