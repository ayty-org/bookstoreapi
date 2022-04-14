package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.service.BookService;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PurchaseServiceTest {
//
//    @InjectMocks
//    private PurchaseService purchaseService;
//    @Mock
//    private PurchaseRepository repository;
//    @Mock
//    private ClientService clientService;
//    @Mock
//    private BookService bookService;
//    private Purchase purchase;
//    private final Map<Double, Book> amountBooks = new LinkedHashMap<>();
//
//
//    @BeforeEach
//    void setUp(){
//        Purchase purchase = new Purchase();
//        purchase.setId(1L);
//        this.purchase = purchase;
//
//        Book book25 = new Book();
//        book25.setId(1L);
//        book25.setPrice(25.0);
//        Book book50 = new Book();
//        book50.setId(2L);
//        book50.setPrice(50.0);
//        Book book100 = new Book();
//        book100.setId(3L);
//        book100.setPrice(100.0);
//
//        amountBooks.put(25.0, book25);
//        amountBooks.put(50.0, book50);
//        amountBooks.put(100.0, book100);
//
//
//    }
//
//    @Test
//    void findByIdWhenIdExistTest(){
//
//        when(repository.findById(1L)).thenReturn(Optional.of(purchase));
//
//        Purchase purchaseSaved = purchaseService.findById(1L);
//        assertThat(purchase, is(purchaseSaved));
//    }
//
//    @Test
//    void findByIdWhenIdDontExist(){
//        when(repository.findById(anyLong())).thenReturn(Optional.empty());
//        assertThrows(EntityNotFoundException.class, ()-> purchaseService.findById(1L));
//    }
//
//    @Test
//    void getAmountToPayTest(){
//
//        List<Book> amount50 = new ArrayList<>();
//        amount50.add(amountBooks.get(25.0));
//        amount50.add(amountBooks.get(25.0));
//        List<Book> amount75 = new ArrayList<>();
//        amount75.add(amountBooks.get(50.0));
//        amount75.add(amountBooks.get(25.0));
//        List<Book> amount100 = new ArrayList<>();
//        amount100.add(amountBooks.get(100.0));
//
//        when(bookService.findById(1L)).thenReturn(amountBooks.get(25.0));
//        when(bookService.findById(2L)).thenReturn(amountBooks.get(50.0));
//        when(bookService.findById(3L)).thenReturn(amountBooks.get(100.0));
//
//        assertThat(50.0, is(equalTo(purchaseService.getAmountToPay(amount50))));
//        assertThat(75.0, is(equalTo(purchaseService.getAmountToPay(amount75))));
//        assertThat(100.0, is(equalTo(purchaseService.getAmountToPay(amount100))));
//    }
//
//    @Test
//    void updateBookStockTest(){
//        Book quantity1 = new Book();
//        quantity1.setId(1L);
//        quantity1.setQuantityInStock(1);
//        Book quantity3 = new Book();
//        quantity3.setId(2L);
//        quantity3.setQuantityInStock(3);
//
//        List<Book> books = new ArrayList<>();
//        books.add(quantity1);
//        books.add(quantity3);
//
//        purchaseService.updateBooksStock(books);
//
//        Book quantity0 = new Book();
//        quantity0.setId(3L);
//        quantity0.setQuantityInStock(0);
//        books.add(quantity0);
//
//        assertThrows(IllegalArgumentException.class, ()-> purchaseService.updateBooksStock(books));
//    }
}