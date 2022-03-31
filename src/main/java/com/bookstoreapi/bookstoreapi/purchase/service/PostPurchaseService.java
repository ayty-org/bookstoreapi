package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;

@FunctionalInterface
public interface PostPurchaseService {

    public PurchaseDTO save(PurchaseDTO purchaseDTO);
}
