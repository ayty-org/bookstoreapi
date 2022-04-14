package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;

@FunctionalInterface
public interface GetPurchaseService {

    Purchase findById(Long id);
}
