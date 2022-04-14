package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.book.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/books")
@RestController
public class BookController {

    private final GetAllBookService getAllBookService;
    private final GetBookService getBookService;
    private final GetAllByCategoryNameBookService getAllByCategoryNameBookService;
    private final PostBookService postBookService;
    private final PutBookService putBookService;
    private final DeleteBookService deleteBookService;


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
    public BookDTO save(@RequestBody @Valid BookDTO bookDTO){
        return postBookService.save(BookDTO.to(bookDTO));
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO update(@PathVariable Long bookId, @RequestBody @Valid BookDTO bookDTO){
        return putBookService.update(bookId, BookDTO.to(bookDTO));
    }

   @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long bookId){
        deleteBookService.delete(bookId);
    }
}
