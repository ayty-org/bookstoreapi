package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteBookServiceImpl implements DeleteBookService{

    private final BookRepository bookRepository;
    private final PurchaseService purchaseService;
    private final BookService bookService;


    @Override
    public void delete(Long id){
        if(purchaseService.existsByBookId(id)){
            throw new DataIntegrityViolationException(
                    "One or more purchases have this book, it is not possible to delete");
        }
        bookRepository.delete(bookService.findById(id));
    }
}
