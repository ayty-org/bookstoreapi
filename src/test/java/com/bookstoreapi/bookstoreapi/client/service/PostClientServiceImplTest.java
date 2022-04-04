package com.bookstoreapi.bookstoreapi.client.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
class PostClientServiceImplTest {

    @InjectMocks
    private PostClientServiceImpl postClientService;
    @Mock
    private ClientRepository repository;



    @Test
    void saveTest(){

        Client client = new Client();
        client.setId(1L);
        client.setName("Ana Júlia");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Ana Júlia");
        clientDTO.setEmail("aninha@hotmail.com");
        clientDTO.setAge(25);
        clientDTO.setGender("Female");
        clientDTO.setTelephone("11111111111");
        when(repository.save(client)).thenReturn(client);

        assertInstanceOf(ClientDTO.class, postClientService.save(clientDTO));
        assertEquals("Ana Júlia", postClientService.save(clientDTO).getName());
    }
}