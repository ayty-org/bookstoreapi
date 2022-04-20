package com.bookstoreapi.bookstoreapi.book.service.abstracts;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public abstract class FindByUuidBookAbstract {

    @Autowired
    private BookRepository bookRepository;

    public Book findByUuid(UUID uuid) throws EntityNotFoundException{
        return this.bookRepository.findByUuid(uuid)
                .orElseThrow(()-> new EntityNotFoundException(uuid, Book.class.getSimpleName()));
    }
}
