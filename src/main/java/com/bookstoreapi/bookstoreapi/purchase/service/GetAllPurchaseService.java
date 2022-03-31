package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;

import java.util.List;

@FunctionalInterface
public interface GetAllPurchaseService {

    List<PurchaseDTO> findAll();
}
