package com.bookstoreapi.bookstoreapi.book;

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


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> list(){
        return bookService.findAll();
    }

    @GetMapping("/{bookId}")
    public Book find(@PathVariable Long bookId){
        return bookService.findById(bookId);
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
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFound(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
