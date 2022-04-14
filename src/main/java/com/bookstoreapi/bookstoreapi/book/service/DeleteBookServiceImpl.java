package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteBookServiceImpl implements DeleteBookService{

    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;


    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(bookRepository.existsById(id)){
            if(purchaseRepository.existsByPurchasedBooksId(id)){
                throw new DeleteException(id, BookDTO.getClassName());
            }
            bookRepository.delete(bookRepository.getById(id));
        }else{
            throw new EntityNotFoundException(id, BookDTO.getClassName());
        }
    }
}
