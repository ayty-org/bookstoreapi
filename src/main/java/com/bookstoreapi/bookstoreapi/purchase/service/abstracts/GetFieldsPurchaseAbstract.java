package com.bookstoreapi.bookstoreapi.purchase.service.abstracts;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.BookOutOfStockException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetFieldsPurchaseAbstract extends FindByUuidPurchaseAbstract{

    @Autowired
    private  BookRepository bookRepository;
    @Autowired
    private  ClientRepository clientRepository;


    public List<Book> getBooksByUuid(List<Book> books) throws EntityNotFoundException{
        List<Book> bookList= new ArrayList<>();
        for(Book book: books){
            bookList.add(bookRepository.findByUuid(book.getUuid()).orElseThrow(
                    ()-> new EntityNotFoundException(book.getUuid(), Book.class.getSimpleName())));
        }
        return bookList;
    }

    public Client getClientByUuid(UUID id) throws EntityNotFoundException {
        return clientRepository.findByUuid(id).orElseThrow(
                () -> new EntityNotFoundException(id, Client.class.getSimpleName()));
    }

    public double getAmountToPay(List<Book> books){
        double amount = 0.0;
        for(Book book: books){
            amount += book.getPrice();
        }
        return amount;
    }

    public void updateBooksStockToDown(List<Book> books) throws BookOutOfStockException{
        List<Book> booksToUpdate = new ArrayList<>();
        for(Book book: books){
            if(book.getQuantityInStock() > 0){
                book.setQuantityInStock(book.getQuantityInStock()-1);
                booksToUpdate.add(book);
            }else{
                throw new BookOutOfStockException(book.getUuid());
            }
        }
        bookRepository.saveAll(booksToUpdate);
    }

    public void updateBooksStockToUp(List<Book> books){
        List<Book> booksToUpdate = new ArrayList<>();
        for(Book book: books){
            book.setQuantityInStock(book.getQuantityInStock()+1);
            booksToUpdate.add(book);
        }
        bookRepository.saveAll(booksToUpdate);
    }
}
