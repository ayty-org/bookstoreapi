package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.service.BookService;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.client.service.ClientService;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;
    @Mock
    private PurchaseRepository repository;
    @Mock
    private ClientService clientService;
    @Mock
    private BookService bookService;

    @Test
    void findByIdWhenIdExistTest(){
        Purchase purchase = new Purchase();
        purchase.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(purchase));

        Purchase purchaseSaved = purchaseService.findById(1L);
        assertThat(purchase, is(purchaseSaved));
    }

    @Test
    void findByIdWhenIdDontExist(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> purchaseService.findById(1L));
    }

    @Test
    void getAmountToPayTest(){

        Book book25 = new Book();
        book25.setId(1L);
        book25.setPrice(25.0);
        Book book50 = new Book();
        book50.setId(2L);
        book50.setPrice(50.0);
        Book book100 = new Book();
        book100.setId(3L);
        book100.setPrice(100.0);

        List<Book> amount50 = new ArrayList<>();
        amount50.add(book25);
        amount50.add(book25);

        List<Book> amount75 = new ArrayList<>();
        amount75.add(book50);
        amount75.add(book25);

        List<Book> amount100 = new ArrayList<>();
        amount100.add(book100);

        when(bookService.findById(1L)).thenReturn(book25);
        when(bookService.findById(2L)).thenReturn(book50);
        when(bookService.findById(3L)).thenReturn(book100);


        assertThat(50.0, is(equalTo(purchaseService.getAmountToPay(amount50))));
        assertThat(75.0, is(equalTo(purchaseService.getAmountToPay(amount75))));
        assertThat(100.0, is(equalTo(purchaseService.getAmountToPay(amount100))));
    }

    @Test
    void updateBookStockTest(){

        Book quantity1 = new Book();
        quantity1.setId(1L);
        quantity1.setQuantityInStock(1);
        Book quantity3 = new Book();
        quantity3.setId(2L);
        quantity3.setQuantityInStock(3);


        List<Book> books = new ArrayList<>();
        books.add(quantity1);
        books.add(quantity3);

        purchaseService.updateBooksStock(books);

        Book quantity0 = new Book();
        quantity0.setId(3L);
        quantity0.setQuantityInStock(0);
        books.add(quantity0);

        assertThrows(IllegalArgumentException.class, ()-> purchaseService.updateBooksStock(books));
    }
}