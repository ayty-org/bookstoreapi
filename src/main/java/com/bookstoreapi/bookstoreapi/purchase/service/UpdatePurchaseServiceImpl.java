package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UpdatePurchaseServiceImpl implements UpdatePurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;


    @Override
    public Purchase update(Long id, Purchase purchase){
        Optional<Purchase> purchaseSaved = purchaseRepository.findById(id);
        if(purchaseSaved.isPresent()){
            List<Book> booksFromOld = getBooksByUuid(purchaseSaved.get().getPurchasedBooks());
            List<Book> books = getBooksByUuid(purchase.getPurchasedBooks());
            purchase.setPurchasedBooks(books);
            purchase.setClient(getClientByUuid(purchase.getClient().getUuid()));
            purchase.setAmount(getAmountToPay(purchase.getPurchasedBooks()));
            purchase.setId(id);
            purchase.setPurchaseDate(purchaseSaved.get().getPurchaseDate());
            updateBooksStock(books, booksFromOld);
            purchase.setPurchaseDate(new Date());
            return purchaseRepository.save(purchase);
        }
        throw new EntityNotFoundException(id, PurchaseDTO.getClassName());
    }

    private void updateBooksStock(List<Book> updated, List<Book> old){
          this.updateBooksStockToUp(old);
          this.updateBooksStockToDown(updated);
    }

    private List<Book> getBooksByUuid(List<Book> books){
        List<Book> bookList= new ArrayList<>();
        for(Book book: books){
            bookList.add(bookRepository.findByUuid(book.getUuid()).orElseThrow(
                    ()-> new EntityNotFoundException(null, null)));
        }
        return bookList;
    }

    private Client getClientByUuid(UUID id){
        return clientRepository.findByUuid(id).orElseThrow(()->{
            throw new EntityNotFoundException(null, null);
        });
    }


    private double getAmountToPay(List<Book> books){
        double amount = 0.0;
        for(Book book: books){
            amount += book.getPrice();
        }
        return amount;
    }


    private void updateBooksStockToUp(List<Book> books){
        List<Book> booksToUpdate = new ArrayList<>();
        for(Book book: books){
            book.setQuantityInStock(book.getQuantityInStock()+1);
            booksToUpdate.add(book);
        }
        bookRepository.saveAll(booksToUpdate);
    }

    private void updateBooksStockToDown(List<Book> books){
        List<Book> booksToUpdate = new ArrayList<>();
        for(Book book: books){
            if(book.getQuantityInStock() > 0){
                book.setQuantityInStock(book.getQuantityInStock()-1);
                booksToUpdate.add(book);
            }else{
                throw new IllegalArgumentException("book with id "+book.getId()+" is out of stock");
            }
        }
        bookRepository.saveAll(booksToUpdate);
    }
}
