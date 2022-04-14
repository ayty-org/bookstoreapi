package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetBookServiceImplTest {
//
//    @InjectMocks
//    private GetBookServiceImpl getBookService;
//    @Mock
//    private BookService service;
//    Map<Long, Book> books = new HashMap<>();
//
//
//    @BeforeEach
//    void setUp(){
//        Book book1 = new Book();
//        book1.setId(1L);
//        book1.setTitle("Book 1");
//
//        Book book2 = new Book();
//        book2.setId(2L);
//        book2.setTitle("Book 2");
//
//        books.put(1L, book1);
//        books.put(2L, book2);
//    }
//
//    @Test
//    void testGetByIdWhenIdExist(){
//        when(service.findById(1L)).thenReturn(books.get(1L));
//        when(service.findById(2L)).thenReturn(books.get(2L));
//
//        BookDTO bookDTO = getBookService.findById(1L);
//        assertThat("Book 1", is(equalTo(bookDTO.getTitle())));
//        bookDTO = getBookService.findById(2L);
//        assertThat("Book 2", is(equalTo(bookDTO.getTitle())));
//    }
//
//    @Test
//    void testGetByIdWhenIdDontExist(){
//        when(service.findById(anyLong())).thenThrow(new IllegalArgumentException());
//        assertThrows(IllegalArgumentException.class, ()-> getBookService.findById(3L));
//    }
}