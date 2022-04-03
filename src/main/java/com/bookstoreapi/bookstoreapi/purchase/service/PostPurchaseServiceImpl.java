package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostPurchaseServiceImpl implements PostPurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseService purchaseService;


    @Override
    public PurchaseDTO save(PurchaseDTO purchaseDTO){
        purchaseDTO.setClient(purchaseService.getClient(purchaseDTO.getClient()));
        purchaseDTO.setPurchasedBooks(purchaseService.getBooks(purchaseDTO.getPurchasedBooks()));
        purchaseDTO.setAmount(purchaseService.getAmountToPay(purchaseDTO.getPurchasedBooks()));
        purchaseDTO.setPurchaseDate(new Date());
        purchaseService.updateBooksStock(purchaseDTO.getPurchasedBooks());
        purchaseRepository.save(new Purchase(purchaseDTO));
        return purchaseDTO;
    }

}
