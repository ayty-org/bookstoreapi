package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl implements SaveBookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Book save(Book book){
        book.setCategories(this.getCategories(book.getCategories()));
        return bookRepository.save(book);
    }

    private List<Category> getCategories(List<Category> categories) {
        List<Category> categoriesSaved = new ArrayList<>();
        for (Category category : categories) {
            if (categoryRepository.existsById(category.getId())) {
                categoriesSaved.add(categoryRepository.findById(category.getId()).get());
            } else {
                throw new EntityNotFoundException(category.getId(), CategoryDTO.getClassName());
            }
        }
        return categoriesSaved;
    }
}
