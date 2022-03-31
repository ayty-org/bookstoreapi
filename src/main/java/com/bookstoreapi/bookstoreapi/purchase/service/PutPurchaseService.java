package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseResumedDTO;

@FunctionalInterface
public interface PutPurchaseService {

    PurchaseResumedDTO update(Long id, PurchaseDTO purchaseDTO);
}
