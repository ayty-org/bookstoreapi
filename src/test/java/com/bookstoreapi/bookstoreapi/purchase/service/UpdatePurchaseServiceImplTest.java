package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.builders.BookBuilder;
import com.bookstoreapi.bookstoreapi.builders.ClientBuilder;
import com.bookstoreapi.bookstoreapi.builders.PurchaseBuilder;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UpdatePurchaseServiceImplTest {

    private UpdatePurchaseServiceImpl updatePurchaseService;
    @Mock
    private PurchaseRepository repository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp(){
      this.updatePurchaseService = new UpdatePurchaseServiceImpl(repository, bookRepository, clientRepository);
    }

    @Test
    void updateTest() throws Exception{
        when(clientRepository.existsById(1L)).thenReturn(true);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(ClientBuilder.clientJenipapo1()));

        when(bookRepository.existsById(anyLong())).thenReturn(true);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(BookBuilder.book1L()));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(BookBuilder.book2L()));
        when(bookRepository.findById(3L)).thenReturn(Optional.of(BookBuilder.book3L()));
        when(repository.save(any())).thenReturn(PurchaseBuilder.purchase1L());
        when(bookRepository.saveAll(any())).thenReturn(BookBuilder.bookList());
        when(repository.findById(1L)).thenReturn(Optional.of(PurchaseBuilder.purchase1L()));

        Purchase purchase = updatePurchaseService.update(null, PurchaseBuilder.purchase1L());

        verify(repository, times(1)).save(any());

        assertThat(1L, Matchers.is(purchase.getId()));
        assertThat(1L, Matchers.is(purchase.getClient().getUuid()));
        assertThat("Jenipapo", Matchers.is(purchase.getClient().getName()));
        assertThat(3, Matchers.is(purchase.getPurchasedBooks().size()));
        assertThat(100.0, Matchers.is(purchase.getAmount()));
        assertThat(new Date(14112020), Matchers.is(purchase.getPurchaseDate()));
        assertThat(true, Matchers.is(purchase.getIsCompleted()));
    }

    @Test
    void updateWhenIdDontExistTest(){
        when(repository.existsById(2L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class,
                () -> updatePurchaseService.update(null, PurchaseBuilder.purchase2L()));
        verify(repository, never()).save(any());

    }

    @Test
    void updateWhenClientDontExistTest() {
        when(clientRepository.existsById(2L)).thenReturn(false);
        when(repository.existsById(2L)).thenReturn(true);

        assertThrows(EntityNotFoundException.class,
                () -> updatePurchaseService.update(null, PurchaseBuilder.purchase2L()));
        verify(repository, never()).save(any());
    }

    @Test
    void updateWhenBookDontExistTest(){
        when(clientRepository.existsById(2L)).thenReturn(true);
        when(clientRepository.findById(2L)).thenReturn(Optional.of(ClientBuilder.clientJenipapo1()));

        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(BookBuilder.book1L()));
        when(bookRepository.existsById(2L)).thenReturn(false);

        when(repository.existsById(2L)).thenReturn(true);

        assertThrows(EntityNotFoundException.class,
                ()->updatePurchaseService.update(null,PurchaseBuilder.purchase2L()));
        verify(repository, never()).save(any());
    }
}