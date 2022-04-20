package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.book.service.abstracts.FindByUuidBookAbstract;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@RequiredArgsConstructor
@Service
public class GetBookServiceImpl extends FindByUuidBookAbstract implements GetBookService{

    private final BookRepository bookRepository;


    public Book getByUuid(UUID id) throws EntityNotFoundException{
        return this.findByUuid(id);
    }
}
