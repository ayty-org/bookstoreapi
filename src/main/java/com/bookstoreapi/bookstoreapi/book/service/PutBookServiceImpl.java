package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.service.GetCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PutBookServiceImpl implements PutBookService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PostBookService postBookService;
    @Autowired
    private GetCategoryService getCategoryService;


    @Override
    public BookDTO update(Long id, BookDTO bookDTO){
        Book bookSaved = this.findById(id);
        categoriesExists(bookSaved.getCategories());
        BeanUtils.copyProperties(bookDTO, bookSaved);
        return new BookDTO(bookRepository.save(bookSaved));
    }

    private Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Book with id " + id + " not found");
        });

    }

    private void categoriesExists(List<Category> categories){
        for(Category category: categories){
            getCategoryService.findById(category.getId());
        }
    }
}
