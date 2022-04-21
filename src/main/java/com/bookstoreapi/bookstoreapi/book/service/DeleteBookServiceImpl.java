package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteBookServiceImpl implements DeleteBookService {

    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;


    @Override
    public void delete(UUID id) throws EntityNotFoundException, DeleteException {
        Optional<Book> bookOptional = bookRepository.findByUuid(id);
        if(bookOptional.isPresent()){
            if (purchaseRepository.existsByPurchasedBooksUuid(id)) {
                throw new DeleteException(id, Book.class.getSimpleName());
            }
            bookRepository.delete(bookOptional.get());
        }else{
            throw new EntityNotFoundException(id, Book.class.getSimpleName());
        }

    }

}
