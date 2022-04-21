package com.bookstoreapi.bookstoreapi.purchase.service.abstracts;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class FindByUuidPurchaseAbstract {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchase findByUuid(UUID uuid) throws EntityNotFoundException {
        return this.purchaseRepository.findByUuid(uuid)
                .orElseThrow(()-> new EntityNotFoundException(uuid, Book.class.getSimpleName()));
    }
}
