package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;

import java.util.List;

@FunctionalInterface
public interface PostPurchaseService {

    Purchase save(Purchase purchase, Long client, List<Long> books);
}
