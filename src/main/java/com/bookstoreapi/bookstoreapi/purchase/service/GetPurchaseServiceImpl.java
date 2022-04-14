package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GetPurchaseServiceImpl implements GetPurchaseService{

    private final PurchaseRepository purchaseRepository;


    public Purchase findById(Long id){
        return purchaseRepository.findById(id).orElseThrow(()-> {
                    throw new EntityNotFoundException(id, PurchaseDTO.getClassName());
                }
        );
    }
}
