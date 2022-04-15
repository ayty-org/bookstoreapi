package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.builders.BookBuilder;
import com.bookstoreapi.bookstoreapi.exception.DeleteException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class DeleteBookServiceImplTest {

    private DeleteBookServiceImpl deleteBookService;
    @Mock
    private BookRepository repository;
    @Mock
    private PurchaseRepository purchaseRepository;


    @BeforeEach
    void setUp() {
       this.deleteBookService = new DeleteBookServiceImpl(repository, purchaseRepository);
    }

    @Test
    void deleteWhenIdExistTest(){
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.of(BookBuilder.book1L()));

        deleteBookService.delete(1L);
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteWhenIdDontExistTest(){
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, ()-> deleteBookService.delete(1L));
        verify(repository, never()).findById(1L);
        verify(repository, never()).delete(any());

    }

    @Test
    void deleteWhenExistPurchaseWithClientTest(){
        when(repository.existsById(1L)).thenReturn(true);
        when(purchaseRepository.existsByPurchasedBooksId(1L)).thenReturn(true);

        assertThrows(DeleteException.class, ()-> deleteBookService.delete(1L));
        verify(repository, never()).findById(1L);
        verify(repository, never()).delete(any());
    }
}