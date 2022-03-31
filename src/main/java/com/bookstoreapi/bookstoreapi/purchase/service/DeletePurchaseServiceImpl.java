package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DeletePurchaseServiceImpl implements DeletePurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public void delete(Long id){
        purchaseRepository.delete(this.findById(id));
    }

    private Purchase findById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(() ->{
            throw new EntityNotFoundException("purchase with id "+id+" not found");
        });
    }

}
