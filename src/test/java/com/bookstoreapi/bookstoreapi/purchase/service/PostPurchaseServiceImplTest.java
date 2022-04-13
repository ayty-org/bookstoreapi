package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PostPurchaseServiceImplTest {
//
//    @InjectMocks
//    private PostPurchaseServiceImpl postPurchaseService;
//    @Mock
//    private PurchaseRepository repository;
//    @Mock
//    private PurchaseService purchaseService;
//    private Purchase purchase;
//
//
//    @BeforeEach
//    void setUp(){
//        Purchase purchase = new Purchase();
//        purchase.setId(1L);
//
//        Book book = new Book();
//        book.setTitle("a");
//        book.setPrice(50.0);
//        book.setQuantityInStock(1);
//        List<Book> books = new ArrayList<>();
//        books.add(book);
//        purchase.setPurchasedBooks(books);
//
//        Client client = new Client();
//        client.setId(1L);
//        purchase.setClient(client);
//        this.purchase = purchase;
//    }
//    @Test
//    void saveTest(){
//        PurchaseDTO purchaseDTO = new PurchaseDTO(purchase);
//        Client client2 = new Client();
//        client2.setId(2L);
//        client2.setName("Ana Júlia");
//        purchaseDTO.setClient(client2);
//
//        when(repository.save(purchase)).thenReturn(purchase);
//        when(purchaseService.getClient(client2)).thenReturn(client2);
//        when(purchaseService.getBooks(any())).thenReturn(purchase.getPurchasedBooks());
//
//        assertInstanceOf(PurchaseDTO.class, postPurchaseService.save(purchaseDTO));
//        assertEquals("Ana Júlia", postPurchaseService.save(purchaseDTO).getClient().getName());
//    }
//
//    @Test
//    void saveWhenFieldsDontExist(){
//        when(purchaseService.getClient(any())).thenThrow(EntityNotFoundException.class);
//        when(purchaseService.getBooks(any())).thenThrow(EntityNotFoundException.class);
//
//        assertThrows(EntityNotFoundException.class, ()->postPurchaseService.save(new PurchaseDTO()));
//    }
}