package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.builders.ClientBuilder;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        when(repository.save(any())).thenReturn(ClientBuilder.clientValid());
        Client client = saveClientServiceImpl.save(ClientBuilder.clientValid());
        assertEquals(1L, client.getId());
        assertEquals("Jenipapo", client.getName());
        assertEquals(19, client.getAge());
        assertEquals("jenipapo@coldmail.com", client.getEmail());
        assertEquals("83996438691", client.getTelephone());
        assertEquals("Male", client.getGender());    }
}