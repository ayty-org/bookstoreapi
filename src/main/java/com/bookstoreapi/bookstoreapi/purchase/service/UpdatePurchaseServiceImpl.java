package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.exception.BookOutOfStockException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import com.bookstoreapi.bookstoreapi.purchase.service.abstracts.GetFieldsPurchaseAbstract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdatePurchaseServiceImpl extends GetFieldsPurchaseAbstract implements UpdatePurchaseService {

    private final PurchaseRepository purchaseRepository;


    @Override
    public Purchase update(UUID uuid, Purchase purchase) throws EntityNotFoundException, BookOutOfStockException {
        Optional<Purchase> purchaseSaved = purchaseRepository.findByUuid(uuid);
        if(purchaseSaved.isPresent()){
            List<Book> booksFromOld = getBooksByUuid(purchaseSaved.get().getPurchasedBooks());
            List<Book> books = getBooksByUuid(purchase.getPurchasedBooks());
            purchase.setPurchasedBooks(books);
            purchase.setClient(getClientByUuid(purchase.getClient().getUuid()));
            purchase.setAmount(getAmountToPay(purchase.getPurchasedBooks()));
            purchase.setId(purchaseSaved.get().getId());
            purchase.setUuid(uuid);
            purchase.setPurchaseDate(purchaseSaved.get().getPurchaseDate());
            updateBooksStock(books, booksFromOld);
            purchase.setPurchaseDate(new Date());
            return purchaseRepository.save(purchase);
        }
        throw new EntityNotFoundException(uuid, Purchase.class.getSimpleName());
    }

    private void updateBooksStock(List<Book> updated, List<Book> old) throws BookOutOfStockException{
          this.updateBooksStockToUp(old);
          this.updateBooksStockToDown(updated);
    }

}
