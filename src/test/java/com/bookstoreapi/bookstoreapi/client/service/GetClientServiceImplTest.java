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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
        when(clientRepository.findById(1L)).thenReturn(Optional.of(ClientBuilder.clientJenipapo1()));

        Client client = getClientService.findById(1L);
        assertThat(1L, is(client.getId()));
        assertThat("Jenipapo", is(client.getName()));
        assertThat(19, is(client.getAge()));
        assertThat("jenipapo@coldmail.com", is(client.getEmail()));
        assertThat("83996438691", is(client.getTelephone()));
        assertThat("Male", is(client.getGender()));
    }

   @Test
    void testGetByIdWhenIdDontExist(){
       when(clientRepository.findById(2L)).thenReturn(Optional.empty());
       assertThrows(EntityNotFoundException.class, ()-> getClientService.findById(2L));
   }
}