package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class ClientServiceTest {
//
//    @InjectMocks
//    private ClientService clientService;
//    @Mock
//    private ClientRepository repository;
//    private Client client;
//
//    @BeforeEach
//    void setUp(){
//        Client client = new Client();
//        client.setId(1L);
//        client.setName("Ana Júlia");
//        this.client = client;
//    }
//
//    @Test
//    void findByIdWhenIdExistTest(){
//        when(repository.findById(anyLong())).thenReturn(Optional.of(client));
//
//        Client client3 = clientService.findById(1L);
//        assertThat("Ana Júlia", is(client3.getName()));
//    }
//
//    @Test
//    void findByIdWhenIdDontExist(){
//        when(repository.findById(anyLong())).thenReturn(Optional.empty());
//        assertThrows(EntityNotFoundException.class, ()-> clientService.findById(1L));
//    }
}