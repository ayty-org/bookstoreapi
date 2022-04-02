package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;

@FunctionalInterface
public interface PutPurchaseService {

    PurchaseDTO update(Long id, PurchaseDTO purchaseDTO);
}
