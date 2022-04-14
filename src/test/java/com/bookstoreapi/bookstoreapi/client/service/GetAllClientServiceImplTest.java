package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.builders.ClientBuilder;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetAllClientServiceImplTest {

    private GetAllClientServiceImpl getAllClientService;
    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    void setUp(){
        this.getAllClientService = new GetAllClientServiceImpl(clientRepository);
    }

    @Test
    void findAllTest(){
        when(clientRepository.findAll()).thenReturn(ClientBuilder.clientList());

        List<Client> clients = getAllClientService.findAll();

        assertThat(3, is(clients.size()));
        assertThat(1L, is(clients.get(0).getId()));
        assertThat(2L, is(clients.get(1).getId()));
        assertThat(3L, is(clients.get(2).getId()));
    }

}