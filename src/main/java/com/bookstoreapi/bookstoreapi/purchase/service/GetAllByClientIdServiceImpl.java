package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAllByClientIdServiceImpl implements GetAllByClientIdService{

    private final PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAllByClientId(UUID clientId) {
        return purchaseRepository.findAllByClientUuid(clientId);
    }
}
