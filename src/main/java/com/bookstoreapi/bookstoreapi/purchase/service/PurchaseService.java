package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.service.BookService;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.service.ClientService;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private ClientService clientService;


    public Purchase findById(Long id){
        return purchaseRepository.findById(id).orElseThrow(()-> {
                    throw new EntityNotFoundException("purchase with id "+id+" not found");
                }
        );
    }

    public double getAmountToPay(List<Book> books){
        double amount = 0.0;
        for(Book book: books){
            amount += bookService.findById(book.getId()).getPrice();
        }
        return amount;
    }

    public Client getClient(Client client){
        return clientService.findById(client.getId());
    }

    public List<Book> getBooks(List<Book> books){
        List<Book> bookList= new ArrayList<>();
        for(Book book: books){
            bookList.add(bookService.findById(book.getId()));
        }
        return bookList;
    }

    public void updateBooksStock(List<Book> books){
        List<Book> booksToUpdate = new ArrayList<>();
        for(Book book: books){
            if(book.getQuantityInStock() > 0){
                book.setQuantityInStock(book.getQuantityInStock()-1);
                booksToUpdate.add(book);
            }else{
                throw new IllegalArgumentException("book with id "+book.getId()+" is out of stock");
            }
        }
        bookService.saveAll(booksToUpdate);
    }

    public boolean existsByBookId(Long id){
        return purchaseRepository.existsByPurchasedBooksId(id);
    }

    public boolean existsByClientId(Long id){
        return purchaseRepository.existsByClientId(id);
    }
}
