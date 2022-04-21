package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.builders.PurchaseBuilder;
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
public class DeletePurchaseServiceImplTest {

    private DeletePurchaseServiceImpl deletePurchaseService;
    @Mock
    private PurchaseRepository repository;


    @BeforeEach
    void setUp() {
        this.deletePurchaseService = new DeletePurchaseServiceImpl(repository);
    }

    @Test
    void deleteWhenIdExistTest() throws Exception{
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.of(PurchaseBuilder.purchase1L()));

        deletePurchaseService.delete(null);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteWhenIdDontExist() throws Exception{

        assertThrows(EntityNotFoundException.class, ()->deletePurchaseService.delete(null));
        verify(repository, never()).delete(any());
    }
}