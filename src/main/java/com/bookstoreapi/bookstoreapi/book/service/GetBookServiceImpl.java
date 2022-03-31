package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class GetBookServiceImpl implements GetBookService{

    @Autowired
    private BookRepository bookRepository;


    @Override
    public BookDTO findById(Long id){
        Book book = bookRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("Book with id "+id+" not found");
        });
        return new BookDTO(book);
    }
}
