package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseResumedDTO;

@FunctionalInterface
public interface PostPurchaseService {

    public PurchaseResumedDTO save(PurchaseDTO purchaseDTO);
}
