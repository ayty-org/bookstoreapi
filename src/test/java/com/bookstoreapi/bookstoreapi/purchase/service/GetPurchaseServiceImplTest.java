package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.builders.PurchaseBuilder;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class GetPurchaseServiceImplTest {

    private GetPurchaseServiceImpl getPurchaseService;
    @Mock
    private PurchaseRepository repository;


    @BeforeEach
    void setUp(){
        this.getPurchaseService = new GetPurchaseServiceImpl(repository);
    }

    @Test
    void GetByIdWhenIdExistTest(){
        when(repository.findById(1L)).thenReturn(Optional.of(PurchaseBuilder.purchase1L()));

        Purchase purchase = getPurchaseService.findById(1L);

        verify(repository, times(1)).findById(1L);
        assertThat(1L, is(purchase.getId()));
        assertThat(1L, is(purchase.getClient().getId()));
        assertThat("Jenipapo", is(purchase.getClient().getName()));
        assertThat(3, is(purchase.getPurchasedBooks().size()));
        assertThat(100.0, is(purchase.getAmount()));
        assertThat(new Date(14112020), is(purchase.getPurchaseDate()));
        assertThat(true, is(purchase.getIsCompleted()));
    }

    @Test
    void GetByIdWhenIdDontExistTest(){
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()->getPurchaseService.findById(1L));
    }
}