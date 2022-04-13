package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetBookServiceImpl implements GetBookService{

    private final BookService bookService;


    @Override
    public BookDTO findById(Long id){
        return BookDTO.from(bookService.findById(id));
    }
}
