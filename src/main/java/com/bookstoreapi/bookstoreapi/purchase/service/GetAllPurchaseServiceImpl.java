package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseResumedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllPurchaseServiceImpl implements GetAllPurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;


    @Override
    public List<PurchaseResumedDTO> findAll() {
        List<PurchaseDTO> list =  purchaseRepository.findAll()
                .stream()
                .map(PurchaseDTO::new).collect(Collectors.toList());

        return list.stream().map(PurchaseResumedDTO::new).collect(Collectors.toList());
    }
}
