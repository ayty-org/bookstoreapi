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
    private final SaveBookService postBookService;
    private final UpdateBookService putBookService;
    private final DeleteBookService deleteBookService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> list(){
        return BookDTO.fromAll(getAllBookService.findAll());
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO find(@PathVariable Long bookId){
        return BookDTO.from(getBookService.findById(bookId));
    }

    @GetMapping("/categories/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findByCategory(@PathVariable String categoryName){
        return BookDTO.fromAll(getAllByCategoryNameBookService.findAllByCategoriesName(categoryName));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO save(@RequestBody @Valid BookRecieveDTO bookDTO){
        return BookDTO.from(postBookService.save(BookRecieveDTO.to(bookDTO)));
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO update(@PathVariable Long bookId, @RequestBody @Valid BookRecieveDTO bookDTO){
        return BookDTO.from(putBookService.update(bookId, BookRecieveDTO.to(bookDTO)));
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long bookId){
        deleteBookService.delete(bookId);
    }
}
