package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseResumedDTO;

import java.util.List;

@FunctionalInterface
public interface GetAllPurchaseService {

    List<PurchaseResumedDTO> findAll();
}
