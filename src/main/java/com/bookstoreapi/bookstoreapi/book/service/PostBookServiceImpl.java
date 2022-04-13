package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostBookServiceImpl implements PostBookService{

    private final BookRepository bookRepository;
    private final BookService bookService;


    @Override
    public BookDTO save(BookDTO bookDTO){
        bookDTO.setCategories(bookService.getCategories(bookDTO.getCategories()));
        bookRepository.save(new Book(bookDTO));
        return bookDTO;
    }
}
