package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.purchase.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DeleteClientServiceImplTest {

    @InjectMocks
    private DeleteClientServiceImpl deleteClientService;
    @Mock
    private ClientRepository repository;
    @Mock
    private PurchaseService purchaseService;
    @Mock
    private ClientService service;
    private final Map<Long, Client> clients = new HashMap<>();


    @BeforeEach
    void setUp() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Jenipapo da Silva");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setName("Fernandin dos Santos");

        clients.put(1L, client1);
        clients.put(2L, client2);
    }

    @Test
    void deleteWhenIdExistTest(){
        when(purchaseService.existsByClientId(anyLong())).thenReturn(false);
        when(service.findById(1L)).thenReturn(clients.get(1L));
        deleteClientService.delete(1L);
    }

    @Test
    void deleteWhenExistPurchaseWithClient(){
        when(purchaseService.existsByClientId(anyLong())).thenReturn(true);
        assertThrows(DataIntegrityViolationException.class, ()-> deleteClientService.delete(1L));
    }
}