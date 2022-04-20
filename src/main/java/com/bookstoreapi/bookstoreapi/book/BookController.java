package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.book.service.*;
import com.bookstoreapi.bookstoreapi.exception.CategoryNotFoundException;
import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
    public BookDTO find(@PathVariable UUID bookId) throws EntityNotFoundException {
        return BookDTO.from(getBookService.getByUuid(bookId));
    }

    @GetMapping("/categories/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findByCategory(@PathVariable String categoryName){
        return BookDTO.fromAll(getAllByCategoryNameBookService.findAllByCategoriesName(categoryName));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO save(@RequestBody @Valid BookRecieveDTO bookDTO) throws CategoryNotFoundException {
        return BookDTO.from(postBookService.save(BookRecieveDTO.to(bookDTO)));
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO update(@PathVariable UUID bookId,
                          @RequestBody @Valid BookRecieveDTO bookDTO)
            throws CategoryNotFoundException, EntityNotFoundException{
        return BookDTO.from(putBookService.update(bookId, BookRecieveDTO.to(bookDTO)));
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID bookId) throws DeleteException, EntityNotFoundException {
        deleteBookService.delete(bookId);
    }
}
