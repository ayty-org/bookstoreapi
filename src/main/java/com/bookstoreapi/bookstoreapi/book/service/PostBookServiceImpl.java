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
    public BookDTO save(Book book){
        book.setCategories(bookService.getCategories(book.getCategories()));
        return BookDTO.from(bookRepository.save(book));
    }
}
