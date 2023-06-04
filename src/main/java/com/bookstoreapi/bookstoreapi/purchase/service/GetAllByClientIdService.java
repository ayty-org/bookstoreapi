package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface GetAllByClientIdService {

    List<Purchase> findAllByClientId(UUID clientId);
}
