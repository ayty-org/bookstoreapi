package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.book.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {

    @Autowired
    private GetAllBookService getAllBookService;
    @Autowired
    private GetBookService getBookService;
    @Autowired
    private GetAllByCategoryNameBookService getAllByCategoryNameBookService;
    @Autowired
    private PostBookService postBookService;
    @Autowired
    private PutBookService putBookService;
    @Autowired
    private DeleteBookService deleteBookService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> list(){
        return getAllBookService.findAll();
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO find(@PathVariable Long bookId){
        return getBookService.findById(bookId);
    }

    @GetMapping("/categories/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findByCategory(@PathVariable String categoryName){
        return getAllByCategoryNameBookService.findAllByCategoriesName(categoryName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO save(@RequestBody @Valid BookDTO book){
        return postBookService.save(book);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO update(@PathVariable Long bookId, @RequestBody @Valid BookDTO book){
        return putBookService.update(bookId,book);
    }

   @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long bookId){
        deleteBookService.delete(bookId);
    }
}
