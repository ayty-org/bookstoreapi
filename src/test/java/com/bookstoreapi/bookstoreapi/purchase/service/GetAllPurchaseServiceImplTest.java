package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.builders.PurchaseBuilder;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetAllPurchaseServiceImplTest {

    private GetAllPurchaseServiceImpl getAllPurchaseService;
    @Mock
    private PurchaseRepository repository;


    @BeforeEach
    void setUp(){
        this.getAllPurchaseService = new GetAllPurchaseServiceImpl(repository);
    }

    @Test
    void findAllTest(){
        when(repository.findAll()).thenReturn(PurchaseBuilder.purchaseList());

        List<Purchase> purchases = getAllPurchaseService.findAll();

        assertThat(2, is(purchases.size()));

        assertThat(1L, is(purchases.get(0).getId()));
        assertThat(1L, is(purchases.get(0).getClient().getId()));
        assertThat("Jenipapo", is(purchases.get(0).getClient().getName()));
        assertThat(3, is(purchases.get(0).getPurchasedBooks().size()));
        assertThat(100.0, is(purchases.get(0).getAmount()));
        assertThat(new Date(14112020), is(purchases.get(0).getPurchaseDate()));
        assertThat(true, is(purchases.get(0).getIsCompleted()));

        assertThat(2L, is(purchases.get(1).getId()));
        assertThat(2L, is(purchases.get(1).getClient().getId()));
        assertThat("Ana", is(purchases.get(1).getClient().getName()));
        assertThat(3, is(purchases.get(1).getPurchasedBooks().size()));
        assertThat(200.0, is(purchases.get(1).getAmount()));
        assertThat(new Date(10102010), is(purchases.get(1).getPurchaseDate()));
        assertThat(false, is(purchases.get(1).getIsCompleted()));
    }
}