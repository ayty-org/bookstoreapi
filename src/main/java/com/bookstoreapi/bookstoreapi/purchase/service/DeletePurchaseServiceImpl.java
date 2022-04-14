package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletePurchaseServiceImpl implements DeletePurchaseService{

    private final PurchaseRepository purchaseRepository;


    @Override
    public void delete(Long id){
        if(purchaseRepository.existsById(id)) {
            purchaseRepository.delete(purchaseRepository.findById(id).get());
        }
        else{
            throw new EntityNotFoundException(id, PurchaseDTO.getClassName());
        }
    }
}
