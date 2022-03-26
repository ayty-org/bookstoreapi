package com.bookstoreapi.bookstoreapi.book;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;


    @ApiOperation(value = "Return a list of all saved books")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> list(){
        return bookService.findAll();
    }

    @ApiOperation(value = "Returns a unique book found by id")
    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book find(@PathVariable Long bookId){
        return bookService.findById(bookId);
    }

    @ApiOperation(value = "Return a list of all saved books with certain category")
    @GetMapping("/categories/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findByCategory(@PathVariable String categoryName){
        return bookService.findAllByCategoriesName(categoryName);
    }

    @ApiOperation(value = "Save a new book")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@RequestBody Book book){
        return bookService.save(book);
    }

    @ApiOperation(value = "Updates all fields of a saved book by the book passed as a parameter")
    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book update(@PathVariable Long bookId, @RequestBody Book book){
        return bookService.update(bookId,book);
    }

    @ApiOperation(value = "Deletes a book by id")
    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long bookId){
        bookService.delete(bookId);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFound(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
