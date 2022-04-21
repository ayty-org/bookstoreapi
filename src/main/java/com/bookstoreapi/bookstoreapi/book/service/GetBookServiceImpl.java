package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@RequiredArgsConstructor
@Service
public class GetBookServiceImpl implements GetBookService{

    private final BookRepository bookRepository;

    @Override
    public Book getByUuid(UUID uuid) throws EntityNotFoundException{
        return this.bookRepository.findByUuid(uuid)
                .orElseThrow(()-> new EntityNotFoundException(uuid, Book.class.getSimpleName()));
    }
}
