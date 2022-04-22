package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.exception.CategoryNotFoundException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateBookServiceImpl implements UpdateBookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Book update(UUID id, Book book) throws EntityNotFoundException, CategoryNotFoundException {
        Optional<Book> bookOptional = bookRepository.findByUuid(id);
        if (bookOptional.isPresent()) {
            book.setCategories(this.getCategoriesByUuid(book.getCategories()));
            book.setId(bookOptional.get().getId());
            book.setUuid(id);
            return bookRepository.save(book);
        }
        throw new EntityNotFoundException(id, Book.class.getSimpleName());
    }

    private List<Category> getCategoriesByUuid(List<Category> categories) throws CategoryNotFoundException {
        List<Category> categoriesSaved = new ArrayList<>();
        for (Category category : categories) {
            categoriesSaved.add(categoryRepository.findById(category.getId())
                    .orElseThrow(() -> new CategoryNotFoundException(category.getId())));
        }
        return categoriesSaved;
    }
}
