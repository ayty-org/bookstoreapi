package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DeleteBookServiceImpl implements DeleteBookService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PurchaseService purchaseService;


    @Override
    public void delete(Long id){
        if(purchaseService.existsByBookId(id)){
            throw new DataIntegrityViolationException(
                    "One or more purchases have this book, it is not possible to delete");
        }
        bookRepository.delete(this.findById(id));
    }

    private Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Book with id " + id + " not found");
        });
    }
}
