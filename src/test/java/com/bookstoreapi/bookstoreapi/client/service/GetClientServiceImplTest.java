package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.builders.ClientBuilder;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class GetClientServiceImplTest {


    private GetClientServiceImpl getClientService;
    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    void setUp(){
        getClientService = new GetClientServiceImpl(clientRepository);
     }

    @Test
    void testGetByIdWhenIdExist(){
        when(clientRepository.findById(1L)).thenReturn(Optional.of(ClientBuilder.clientValid()));

        Client client = getClientService.findById(1L);
        assertEquals(1L, client.getId());
        assertEquals("Jenipapo", client.getName());
        assertEquals(19, client.getAge());
        assertEquals("jenipapo@coldmail.com", client.getEmail());
        assertEquals("83996438691", client.getTelephone());
        assertEquals("Male", client.getGender());
    }

   @Test
    void testGetByIdWhenIdDontExist(){
       when(clientRepository.findById(2L)).thenReturn(Optional.empty());
       assertThrows(EntityNotFoundException.class, ()-> getClientService.findById(2L));
   }
}