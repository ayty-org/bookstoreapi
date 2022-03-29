package com.bookstoreapi.bookstoreapi.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> list(){
        return bookService.findAll();
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book find(@PathVariable Long bookId){
        return bookService.findById(bookId);
    }

    @GetMapping("/categories/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findByCategory(@PathVariable String categoryName){
        return bookService.findAllByCategoriesName(categoryName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@RequestBody Book book){
        return bookService.save(book);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book update(@PathVariable Long bookId, @RequestBody Book book){
        return bookService.update(bookId,book);
    }

   @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long bookId){
        bookService.delete(bookId);
    }
}
