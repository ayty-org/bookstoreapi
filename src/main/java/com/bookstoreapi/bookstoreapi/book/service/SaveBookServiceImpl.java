package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.book.service.abstracts.GetCategoriesAbstract;
import com.bookstoreapi.bookstoreapi.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl extends GetCategoriesAbstract implements SaveBookService {

    private final BookRepository bookRepository;


    @Override
    public Book save(Book book) throws CategoryNotFoundException {
        book.setCategories(this.getCategoriesByUuid(book.getCategories()));
        return bookRepository.save(book);
    }
}
