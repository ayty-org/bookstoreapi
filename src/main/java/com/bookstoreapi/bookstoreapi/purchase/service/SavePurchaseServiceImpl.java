package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.BookOutOfStockException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import com.bookstoreapi.bookstoreapi.purchase.service.abstracts.GetFieldsPurchaseAbstract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl extends GetFieldsPurchaseAbstract implements SavePurchaseService {

    private final PurchaseRepository purchaseRepository;


    @Override
    public Purchase save(Purchase purchase) throws EntityNotFoundException, BookOutOfStockException {
        purchase.setClient(getClientByUuid(purchase.getClient().getUuid()));
        List<Book> books = getBooksByUuid(purchase.getPurchasedBooks());
        purchase.setPurchasedBooks(books);
        purchase.setAmount(getAmountToPay(purchase.getPurchasedBooks()));
        purchase.setPurchaseDate(new Date());
        this.updateBooksStockToDown(books);
        return purchaseRepository.save(purchase);
    }
}
