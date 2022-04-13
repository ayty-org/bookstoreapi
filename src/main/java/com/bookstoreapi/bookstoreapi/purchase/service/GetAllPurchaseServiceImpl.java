package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetAllPurchaseServiceImpl implements GetAllPurchaseService{

    private final PurchaseRepository purchaseRepository;


    @Override
    public List<PurchaseDTO> findAll() {
        return purchaseRepository.findAll()
                .stream()
                .map(PurchaseDTO::new).collect(Collectors.toList());
    }
}
