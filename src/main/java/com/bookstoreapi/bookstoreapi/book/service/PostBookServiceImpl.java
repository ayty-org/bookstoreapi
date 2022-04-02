package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostBookServiceImpl implements PostBookService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;


    @Override
    public BookDTO save(BookDTO bookDTO){
        bookDTO.setCategories(bookService.getCategories(bookDTO.getCategories()));
        bookRepository.save(new Book(bookDTO));
        return bookDTO;
    }
}
