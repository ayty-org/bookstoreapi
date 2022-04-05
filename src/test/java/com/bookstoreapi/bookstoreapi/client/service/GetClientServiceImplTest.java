package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class GetClientServiceImplTest {


    @InjectMocks
    private GetClientServiceImpl getClientService;
    @Mock
    private ClientService service;
    Map<Long, Client> clients = new HashMap<>();


    @BeforeEach
    void setUp(){
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
    void testGetByIdWhenIdExist(){
        when(service.findById(1L)).thenReturn(clients.get(1L));
        when(service.findById(2L)).thenReturn(clients.get(2L));

        ClientDTO clientDTO = getClientService.findById(1L);
        assertThat("Jenipapo da Silva", is(equalTo(clientDTO.getName())));
        clientDTO = getClientService.findById(2L);
        assertThat("Fernandin dos Santos", is(equalTo(clientDTO.getName())));
   }

   @Test
    void testGetByIdWhenIdDontExist(){
       when(service.findById(3L)).thenThrow(new IllegalArgumentException());
       assertThrows(IllegalArgumentException.class, ()-> getClientService.findById(3L));
   }
}