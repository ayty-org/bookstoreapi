package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class PostPurchaseServiceImpl implements PostPurchaseService{

    private final PurchaseRepository purchaseRepository;
    private final PurchaseService purchaseService;


    @Override
    public PurchaseDTO save(Purchase purchase){
        purchase.setClient(purchaseService.getClient(purchase.getClient()));
        purchase.setPurchasedBooks(purchaseService.getBooks(purchase.getPurchasedBooks()));
        purchase.setAmount(purchaseService.getAmountToPay(purchase.getPurchasedBooks()));
        purchase.setPurchaseDate(new Date());
        purchaseService.updateBooksStock(purchase.getPurchasedBooks());
        return PurchaseDTO.from(purchaseRepository.save(purchase));
    }

}
