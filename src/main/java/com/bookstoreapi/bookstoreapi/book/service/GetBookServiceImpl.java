package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBookServiceImpl implements GetBookService{

    @Autowired
    private BookService bookService;


    @Override
    public BookDTO findById(Long id){
        return new BookDTO(bookService.findById(id));
    }
}
