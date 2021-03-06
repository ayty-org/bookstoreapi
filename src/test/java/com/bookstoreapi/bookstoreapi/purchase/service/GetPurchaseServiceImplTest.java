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
import java.util.UUID;

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
    void setUp() {
        this.getPurchaseService = new GetPurchaseServiceImpl(repository);
    }

    @Test
    void GetByIdWhenIdExistTest() throws Exception {
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(PurchaseBuilder.purchase1L()));

        Purchase purchase = getPurchaseService.getByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"));

        verify(repository, times(1)).findByUuid(any());
        assertThat(purchase.getId(), is(1L));
        assertThat(purchase.getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(purchase.getClient().getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(purchase.getClient().getName(), is("Jenipapo"));
        assertThat(purchase.getPurchasedBooks().size(), is(3));
        assertThat(purchase.getAmount(), is(100.00));
        assertThat(purchase.getPurchaseDate(), is(new Date(14112020)));
        assertThat(purchase.getIsCompleted(), is(true));
    }

    @Test
    void GetByIdWhenIdDontExistTest() {
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> getPurchaseService.getByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")));
    }
}