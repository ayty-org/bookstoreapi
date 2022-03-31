package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllBookServiceImpl implements GetAllBookService{

    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<BookDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookDTO::new).collect(Collectors.toList());
    }
}
