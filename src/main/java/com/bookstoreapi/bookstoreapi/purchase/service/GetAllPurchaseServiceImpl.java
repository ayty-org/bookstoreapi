package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllPurchaseServiceImpl implements GetAllPurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;


    @Override
    public List<PurchaseDTO> findAll() {
        return purchaseRepository.findAll()
                .stream()
                .map(PurchaseDTO::new).collect(Collectors.toList());
    }
}
