package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.builders.ClientBuilder;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SaveClientServiceImplTest {

    private SaveClientServiceImpl saveClientServiceImpl;
    @Mock
    private ClientRepository repository;


    @BeforeEach
    void setUp(){
       this.saveClientServiceImpl = new SaveClientServiceImpl(repository);
    }

    @Test
    void saveTest(){
        when(repository.save(any())).thenReturn(ClientBuilder.clientJenipapo1());
        Client client = saveClientServiceImpl.save(ClientBuilder.clientJenipapo1());
        assertThat(1L, is(client.getId()));
        assertThat("Jenipapo", is(client.getName()));
        assertThat(19, is(client.getAge()));
        assertThat("jenipapo@coldmail.com", is(client.getEmail()));
        assertThat("83996438691", is(client.getTelephone()));
        assertThat("Male", is(client.getGender()));
    }
}