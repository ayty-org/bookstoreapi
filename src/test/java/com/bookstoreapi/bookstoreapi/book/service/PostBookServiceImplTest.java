package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.categories.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PostBookServiceImplTest {

    @InjectMocks
    private PostBookServiceImpl postBookService;
    @Mock
    private BookRepository repository;
    @Mock
    private BookService service;


    @Test
    void saveTest(){
        Book book = new Book();
        book.setId(1L);
        book.setTitle("test");

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("test");
        bookDTO.setQuantityInStock(4);
        bookDTO.setAuthorName("blabla da silva bla");
        bookDTO.setPublicationYear(new Date());
        bookDTO.setSynopsis("bla bla bla");
        bookDTO.setCategories(new ArrayList<>());
        when(repository.save(book)).thenReturn(book);
        when(service.getCategories(anyList())).thenReturn(new ArrayList<>());

        assertInstanceOf(BookDTO.class, postBookService.save(bookDTO));
        assertEquals("test", postBookService.save(bookDTO).getTitle());
    }

    @Test
    void saveWhenCategoryDontExistTest(){
        BookDTO bookDTO = new BookDTO();
        Category category = new Category();
        category.setId(1L);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        bookDTO.setCategories(categories);

        when(service.getCategories(anyList())).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, ()-> postBookService.save(bookDTO));

    }
}