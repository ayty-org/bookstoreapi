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
import static org.mockito.Mockito.*;

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
        verify(clientRepository, times(1)).findAll();

        assertThat(1L, is(clients.get(0).getId()));
        assertThat("Jenipapo", is(clients.get(0).getName()));
        assertThat(19, is(clients.get(0).getAge()));
        assertThat("jenipapo@coldmail.com", is(clients.get(0).getEmail()));
        assertThat("83996438691", is(clients.get(0).getTelephone()));
        assertThat("Male", is(clients.get(0).getGender()));

        assertThat(2L, is(clients.get(1).getId()));
        assertThat("Ana", is(clients.get(1).getName()));
        assertThat(46, is(clients.get(1).getAge()));
        assertThat("ana@coldmail.com", is(clients.get(1).getEmail()));
        assertThat("83996438691", is(clients.get(1).getTelephone()));
        assertThat("Female", is(clients.get(1).getGender()));

        assertThat(3L, is(clients.get(2).getId()));
        assertThat("Patricia", is(clients.get(2).getName()));
        assertThat(25, is(clients.get(2).getAge()));
        assertThat("patricia@coldmail.com", is(clients.get(2).getEmail()));
        assertThat("83996438691", is(clients.get(2).getTelephone()));
        assertThat("Trans", is(clients.get(2).getGender()));
    }

}