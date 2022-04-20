package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.book.service.abstracts.GetCategoriesAbstract;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import com.bookstoreapi.bookstoreapi.exception.CategoryNotFoundException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateBookServiceImpl extends GetCategoriesAbstract implements UpdateBookService {

    private final BookRepository bookRepository;


    @Override
    public Book update(UUID id, Book book) throws EntityNotFoundException, CategoryNotFoundException {
        Book bookSaved = this.findByUuid(id);
        book.setId(bookSaved.getId());
        book.setCategories(this.getCategoriesByUuid(book.getCategories()));
        throw new EntityNotFoundException(id, BookDTO.getClassName());
    }
}
