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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class UpdateClientServiceImplTest {

    private UpdateClientServiceImpl updateClientService;
    @Mock
    private ClientRepository repository;


    @BeforeEach
    void setUp(){
        this.updateClientService = new UpdateClientServiceImpl(repository);
    }

    @Test
    void updateTest() throws Exception{
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(any())).thenReturn(ClientBuilder.clientJenipapo1());

        Client client = updateClientService.update(null, ClientBuilder.clientJenipapo1());
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).save(any());

        assertThat(1L, is(client.getId()));
        assertThat("Jenipapo", is(client.getName()));
        assertThat(19, is(client.getAge()));
        assertThat("jenipapo@coldmail.com", is(client.getEmail()));
        assertThat("83996438691", is(client.getTelephone()));
        assertThat("Male", is(client.getGender()));
    }

    @Test
    void updateWhenIdDontExist(){
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class,
                ()-> updateClientService.update(null, ClientBuilder.clientJenipapo1()));
        verify(repository, times(1)).existsById(1L);
        verify(repository, never()).save(any());
    }
}