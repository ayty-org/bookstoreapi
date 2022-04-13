package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PutBookServiceImpl implements PutBookService{

    private final BookRepository bookRepository;
    private final BookService bookService;


    @Override
    public BookDTO update(Long id, BookDTO bookDTO){
        Book bookSaved = bookService.findById(id);
        bookDTO.setCategories(bookService.getCategories(bookDTO.getCategories()));
        BeanUtils.copyProperties(bookDTO, bookSaved);
        return new BookDTO(bookRepository.save(bookSaved));
    }
}
