package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;

@FunctionalInterface
public interface UpdatePurchaseService {

    Purchase update(Long id, Purchase purchase);
}
