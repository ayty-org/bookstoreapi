package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    //private final CategoryService categoryService;


    public Book findById(Long id){
        return bookRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("Book with id "+id+" not found");
        });
    }

    public List<Category> getCategories(List<Category> categories){
        List<Category> categoriesList= new ArrayList<>();
        for(Category category: categories){
           // categoriesList.add(categoryService.findById(category.getId()));
        }
        return categoriesList;
    }

    public void saveAll(List<Book> books){
        bookRepository.saveAll(books);
    }
}
