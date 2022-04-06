package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class PutClientServiceImplTest {


    @InjectMocks
    private PutClientServiceImpl putClientService;
    @Mock
    private ClientRepository repository;
    @Mock
    private ClientService service;
    private Client client;


    @BeforeEach
    void setUp(){
        Client clientOld = new Client();
        clientOld.setId(1L);
        clientOld.setName("old");
        clientOld.setEmail("old@old.com");
        clientOld.setAge(25);
        clientOld.setGender("Female");
        clientOld.setTelephone("11111111111");
        this.client = clientOld;
    }

    @Test
    void updateTest(){
        Client clientUpdated = new Client();
        BeanUtils.copyProperties(client, clientUpdated);
        clientUpdated.setName("updated");
        clientUpdated.setEmail("updated@updated.com");

        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(clientUpdated, clientDTO);

        when(service.findById(anyLong())).thenReturn(client);
        when(repository.save(any())).thenReturn(clientUpdated);

        assertThat("updated", is(equalTo
                (putClientService.update(1L, clientDTO).getName())));
        assertInstanceOf(ClientDTO.class, putClientService.update(1L, clientDTO));
    }

}