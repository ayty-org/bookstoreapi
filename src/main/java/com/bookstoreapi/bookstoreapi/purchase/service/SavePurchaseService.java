package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;

@FunctionalInterface
public interface SavePurchaseService {

    Purchase save(Purchase purchase);
}
