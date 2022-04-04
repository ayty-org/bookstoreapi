package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.BeanUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


@MockitoSettings(strictness = Strictness.LENIENT)
class PutClientServiceImplTest {


    @InjectMocks
    private PutClientServiceImpl putClientService;
    @Mock
    private ClientRepository repository;
    @Mock
    private ClientService service;

    @Test
    void updateTest(){
        Client clientOld = new Client();
        clientOld.setId(1L);
        clientOld.setName("old");
        clientOld.setEmail("old@old.com");
        clientOld.setAge(25);
        clientOld.setGender("Female");
        clientOld.setTelephone("11111111111");

        Client clientUpdated = new Client();
        BeanUtils.copyProperties(clientOld, clientUpdated);
        clientUpdated.setName("updated");
        clientUpdated.setEmail("updated@updated.com");

        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(clientUpdated, clientDTO);

        when(service.findById(anyLong())).thenReturn(clientOld);
        when(repository.save(any())).thenReturn(clientUpdated);
        assertThat("updated", is(equalTo
                (putClientService.update(1L, clientDTO).getName())));
        assertInstanceOf(ClientDTO.class, putClientService.update(1L, clientDTO));
    }

}