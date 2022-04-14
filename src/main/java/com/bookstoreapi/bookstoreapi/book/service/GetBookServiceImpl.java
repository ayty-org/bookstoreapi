package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GetBookServiceImpl implements GetBookService{

    private final BookRepository bookRepository;


    public Book findById(Long id){
        return bookRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException(id, BookDTO.getClassName());
        });
    }
}
