package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.book.service.abstracts.FindByUuidBookAbstract;
import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteBookServiceImpl extends FindByUuidBookAbstract implements DeleteBookService {

    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;


    @Override
    public void delete(UUID id) throws EntityNotFoundException, DeleteException {
        if (purchaseRepository.existsByPurchasedBooksUuid(id)) {
            throw new DeleteException(id, Book.class.getSimpleName());
        }
        bookRepository.delete(this.findByUuid(id));
    }
}
