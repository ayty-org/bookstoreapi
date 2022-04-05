package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.purchase.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DeleteBookServiceImplTest {

    @InjectMocks
    private DeleteBookServiceImpl deleteBookService;
    @Mock
    private BookRepository repository;
    @Mock
    private PurchaseService purchaseService;
    @Mock
    private BookService service;
    private final Map<Long, Book> books = new HashMap<>();


    @BeforeEach
    void setUp() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("book 2");

        books.put(1L, book1);
        books.put(2L, book2);
    }

    @Test
    void deleteWhenIdExistTest(){
        when(purchaseService.existsByBookId(anyLong())).thenReturn(false);
        when(service.findById(1L)).thenReturn(books.get(1L));
        deleteBookService.delete(1L);
    }

    @Test
    void deleteWhenExistPurchaseWithClient(){
        when(purchaseService.existsByBookId(anyLong())).thenReturn(true);
        assertThrows(DataIntegrityViolationException.class, ()-> deleteBookService.delete(1L));
    }
}