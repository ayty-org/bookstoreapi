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

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl implements SavePurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;


    @Override
    public Purchase save(Purchase purchase){
        purchase.setClient(this.getClient(purchase.getClient().getId()));
        purchase.setPurchasedBooks(this.getBooks(purchase.getPurchasedBooks()));
        purchase.setAmount(this.getAmountToPay(purchase.getPurchasedBooks()));
        purchase.setPurchaseDate(new Date());
        this.updateBooksStockToDown(purchase.getPurchasedBooks());
        return purchaseRepository.save(purchase);
    }

    private List<Book> getBooks(List<Book> books){
        List<Book> bookList= new ArrayList<>();
        for(Book book: books){
            if(bookRepository.existsById(book.getId())){
                bookList.add(bookRepository.findById(book.getId()).get());
            }
            else{
                throw new EntityNotFoundException(book.getId(), BookDTO.getClassName());
            }
        }
        return bookList;
    }

    private Client getClient(Long id){
        if(clientRepository.existsById(id)){
            return clientRepository.findById(id).get();
        }
        throw new EntityNotFoundException(id, ClientDTO.getClassName());
    }

    private double getAmountToPay(List<Book> books){
        double amount = 0.0;
        for(Book book: books){
            amount += bookRepository.findById(book.getId()).get().getPrice();
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
                throw new IllegalArgumentException("book with id "+book.getId()+" is out of stock");
            }
        }
        bookRepository.saveAll(booksToUpdate);
    }
}
