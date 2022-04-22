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
import java.util.UUID;

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
    void deleteWhenIdExistTest() throws Exception{
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(BookBuilder.book1L()));

        deleteBookService.delete(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        verify(repository, times(1)).findByUuid(any());
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteWhenIdDontExistTest(){
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                ()-> deleteBookService.delete(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")));
        verify(repository, never()).findById(1L);
        verify(repository, never()).delete(any());
    }

    @Test
    void deleteWhenExistPurchaseWithClientTest(){
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(BookBuilder.book1L()));
        when(purchaseRepository.existsByPurchasedBooksUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(true);

        assertThrows(DeleteException.class,
                ()-> deleteBookService.delete(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")));
        verify(repository, never()).delete(any());
    }
}