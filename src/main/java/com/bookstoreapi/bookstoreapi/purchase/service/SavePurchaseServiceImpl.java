package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl implements SavePurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;


    @Override
    public Purchase save(Purchase purchase){
        purchase.setClient(getClientByUuid(purchase.getClient().getUuid()));
        List<Book> books = getBooksByUuid(purchase.getPurchasedBooks());
        purchase.setPurchasedBooks(books);
        purchase.setAmount(getAmountToPay(purchase.getPurchasedBooks()));
        purchase.setPurchaseDate(new Date());
        this.updateBooksStockToDown(books);
        return purchaseRepository.save(purchase);
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

    private void updateBooksStockToDown(List<Book> books){
        List<Book> booksToUpdate = new ArrayList<>();
        for(Book book: books){
            if(book.getQuantityInStock() > 0){
                book.setQuantityInStock(book.getQuantityInStock()-1);
                booksToUpdate.add(book);
            }else{
                throw new IllegalArgumentException("book with id "+book.getUuid()+" is out of stock");
            }
        }
        bookRepository.saveAll(booksToUpdate);
    }
}
