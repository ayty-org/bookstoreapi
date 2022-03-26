package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryService categoryService;


    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public List<Book> findAllByCategoriesName(String name){
        Category category = categoryService.findByName(name);
        return bookRepository.findAllByCategoriesContaining(category);
    }

    public Book findById(Long id){
        return bookRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("Book with id "+id+" not found");
        });
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public void delete(Long id){
        Book bookSaved = this.findById(id);
        bookRepository.delete(bookSaved);
    }

    public Book update(Long id, Book book){
        Book bookSaved = this.findById(id);
        BeanUtils.copyProperties(book, bookSaved, "id");
        return this.save(bookSaved);
    }
}
