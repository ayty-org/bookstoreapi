package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.builders.ClientBuilder;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
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
public class DeleteClientServiceImplTest {

    private DeleteClientServiceImpl deleteClientService;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private PurchaseRepository purchaseRepository;



    @BeforeEach
    void setUp() {
        this.deleteClientService = new DeleteClientServiceImpl(clientRepository, purchaseRepository);
    }

    @Test
    void deleteWhenIdExistTest() throws Exception{
        when(clientRepository.existsById(1L)).thenReturn(true);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(ClientBuilder.clientJenipapo1()));

        deleteClientService.delete(null);
        verify(clientRepository, times(1)).delete(any());
    }

    @Test
    void deleteWhenIdDontExistTest() throws Exception{
        when(clientRepository.existsById(1L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, ()-> deleteClientService.delete(null));
        verify(clientRepository, never()).delete(any());
        verify(clientRepository, never()).findById(anyLong());
    }

    @Test
    void deleteWhenExistPurchaseWithClient() throws Exception{
        when(clientRepository.existsById(1L)).thenReturn(true);
        when(purchaseRepository.existsByClientUuid(any())).thenReturn(true);
        assertThrows(DeleteException.class, ()-> deleteClientService.delete(null));
        verify(clientRepository, never()).delete(any());
    }
}