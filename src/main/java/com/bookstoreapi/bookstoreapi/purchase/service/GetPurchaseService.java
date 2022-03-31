package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseResumedDTO;

@FunctionalInterface
public interface GetPurchaseService {

    PurchaseResumedDTO findById(Long id);
}
