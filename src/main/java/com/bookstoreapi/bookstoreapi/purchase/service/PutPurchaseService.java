package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;

import java.util.List;

@FunctionalInterface
public interface PutPurchaseService {

    Purchase update(Long id, Purchase purchaseDTO, Long client, List<Long> books);
}
