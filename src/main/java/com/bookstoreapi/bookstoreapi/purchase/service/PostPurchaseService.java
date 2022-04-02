package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;

@FunctionalInterface
public interface PostPurchaseService {

    PurchaseDTO save(PurchaseDTO purchaseDTO);
}
