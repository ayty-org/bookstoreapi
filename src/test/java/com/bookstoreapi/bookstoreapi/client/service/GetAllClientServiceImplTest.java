package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllClientServiceImplTest {

    @InjectMocks
    private GetAllClientServiceImpl getAllClientService;
    @Mock
    private ClientRepository clientRepository;
    private final List<Client> allClients = new LinkedList<>();


    @BeforeEach
    void setUp(){
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Jenipapo da Silva");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setName("Fernandin dos Santos");

        Client client3 = new Client();
        client3.setId(3L);
        client3.setName("Rochele Figueiredo");

        allClients.add(client1);
        allClients.add(client2);
        allClients.add(client3);
    }

    @Test
    void findAllTest(){
        when(clientRepository.findAll()).thenReturn(allClients);
        List<ClientDTO> listReturned = getAllClientService.findAll();
        assertThat(3, is(equalTo(listReturned.size())));
        for(int k = 0; k<3; k++){
            assertThat(allClients.get(k).getName(), is(equalTo(listReturned.get(k).getName())));
        }

    }

}