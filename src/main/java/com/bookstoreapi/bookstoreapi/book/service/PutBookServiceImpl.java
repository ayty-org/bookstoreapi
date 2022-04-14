package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
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
public class PutBookServiceImpl implements PutBookService{

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Book update(Long id, Book book, List<Long> categories) throws EntityNotFoundException{
        if(bookRepository.existsById(id)){
            book.setCategories(this.getCategories(categories));
            book.setId(id);
            return bookRepository.save(book);
        }
        throw new EntityNotFoundException(id, BookDTO.getClassName());
    }

    private List<Category> getCategories(List<Long> ids) throws EntityNotFoundException {
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
