package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllByCategoryNameBookServiceImpl implements GetAllByCategoryNameBookService{

    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<BookDTO> findAllByCategoriesName(String name){
        return bookRepository.findAllByCategoriesNameIgnoreCase(name)
                .stream()
                .map(BookDTO::new).collect(Collectors.toList());
    }
}
