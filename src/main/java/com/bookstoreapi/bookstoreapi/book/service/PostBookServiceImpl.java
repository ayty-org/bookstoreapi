package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.service.GetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostBookServiceImpl implements PostBookService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GetCategoryService getCategoryService;


    @Override
    public BookDTO save(BookDTO bookDTO){
        categoriesExists(bookDTO.getCategories());
        bookRepository.save(new Book(bookDTO));
        return bookDTO;
    }

    private void categoriesExists(List<Category> categories){
        for(Category category: categories){
            getCategoryService.findById(category.getId());
        }
    }
}
