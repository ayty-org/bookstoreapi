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
public class PostBookServiceImpl implements PostBookService{

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Book save(Book book, List<Long> categories){
        book.setCategories(this.getCategories(categories));
        return bookRepository.save(book);
    }

    private List<Category> getCategories(List<Long> ids) {
        List<Category> categoriesSaved = new ArrayList<>();
        for (Long id : ids) {
            if (categoryRepository.existsById(id)) {
                categoriesSaved.add(categoryRepository.findById(id).get());
            } else {
                throw new EntityNotFoundException(id, CategoryDTO.getClassName());
            }
        }
        return categoriesSaved;
    }
}
