package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings
public class GetClientServiceImplTest {

    @InjectMocks
    private GetClientServiceImpl getClientService;
    @Mock
    private ClientService clientService;


    @BeforeEach
    void load(){
     Client client = new Client();
     client.setId(1L);
     client.setName("Jenipapo da Silva");

     when(clientService.findById(1L)).thenReturn(client);
//     when(clientService.findById(2L)).thenThrow(new IllegalArgumentException
//             ("client with id "+2L+" not found"));
     }

    @Test
    void testGetById(){
        ClientDTO clientDTO = getClientService.findById(1L);
        assertThat("Jenipapo da Silva", is(equalTo(clientDTO.getName())));
//
//        assertThat(new IllegalArgumentException
//                ("client with id "+2L+" not found"), is(equalTo(getClientService.findById(2L))));
   }

}