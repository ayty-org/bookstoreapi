package com.bookstoreapi.bookstoreapi.purchase;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;


    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public Purchase findById(Long id){
        return purchaseRepository.findById(id).orElseThrow(() ->{
            throw new EntityNotFoundException("Purchase with id "+id+" not found");
        });
    }

    public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

    public void delete(Long id){
        Purchase purchaseSaved = this.findById(id);
        purchaseRepository.delete(purchaseSaved);
    }

    public Purchase update(Long id,Purchase purchase){
        Purchase purchaseSaved = this.findById(id);
        BeanUtils.copyProperties(purchase,purchaseSaved);
        return this.save(purchaseSaved);
    }
}
