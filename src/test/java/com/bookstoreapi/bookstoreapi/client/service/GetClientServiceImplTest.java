package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@MockitoSettings
public class GetClientServiceImplTest {


    @InjectMocks
    private GetClientServiceImpl getClientService;
    @Mock
    private ClientService service;

    private ClientRepository repository;


    @BeforeEach
    void setUp(){
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Jenipapo da Silva");
        Client client2 = new Client();
        client2.setId(2L);
        client2.setName("Fernandin dos Santos");
        when(service.findById(1L)).thenReturn(client1);
        when(service.findById(2L)).thenReturn(client2);
        when(service.findById(3L)).thenThrow(new IllegalArgumentException
                ("client with id "+2+" not found"));
     }

    @Test
    void testGetById(){
        // when id exists
        ClientDTO clientDTO = getClientService.findById(1L);
        assertThat("Jenipapo da Silva", is(equalTo(clientDTO.getName())));
        clientDTO = getClientService.findById(2L);
        assertThat("Fernandin dos Santos", is(equalTo(clientDTO.getName())));
        // when id don't exists
        try{
            getClientService.findById(3L);
        }catch (IllegalArgumentException e){
            assertThat("client with id "+2L+" not found", is(equalTo(e.getMessage())));
        }
   }
}