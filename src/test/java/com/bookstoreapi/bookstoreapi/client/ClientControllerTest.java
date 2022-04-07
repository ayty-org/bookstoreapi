package com.bookstoreapi.bookstoreapi.client;

import com.bookstoreapi.bookstoreapi.client.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetAllClientServiceImpl getAllClientService;
    @MockBean
    private GetClientServiceImpl getClientService;
    @MockBean
    private PostClientServiceImpl postClientService;
    @MockBean
    private PutClientServiceImpl putClientService;
    @MockBean
    private DeleteClientServiceImpl deleteClientService;
    @MockBean
    private ClientService clientService;


    ObjectMapper mapper = new ObjectMapper();

    private List<ClientDTO> clients = new ArrayList<>();


    @BeforeEach
    void setUp(){
        ClientDTO client1 = new ClientDTO();
        client1.setName("Client 1");
        client1.setAge(20);
        client1.setEmail("blabla@blabla.com");
        client1.setTelephone("11111111111");
        client1.setGender("Male");

        ClientDTO client2 = new ClientDTO();
        client2.setName("Client 2");
        client2.setAge(80);
        client2.setEmail("test.com");
        client2.setTelephone("22222222222");
        client2.setGender("Female");
        clients.add(client1);
        clients.add(client2);
    }

    @Test
    void listTest() throws Exception{
        when(getAllClientService.findAll()).thenReturn(clients);
        mockMvc.perform(get("/clients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].name", is("Client 1")))
                .andExpect(jsonPath("$[0].age", is(20)))
                .andExpect(jsonPath("$[0].telephone", is("11111111111")))
                .andExpect(jsonPath("$[0].email", is("blabla@blabla.com")))
                .andExpect(jsonPath("$[0].gender", is("Male")))
                .andExpect(jsonPath("$[1].name", is("Client 2")))
                .andExpect(jsonPath("$[1].age", is(80)))
                .andExpect(jsonPath("$[1].telephone", is("22222222222")))
                .andExpect(jsonPath("$[1].email", is("test.com")))
                .andExpect(jsonPath("$[1].gender", is("Female")));
    }


    @Test
    void findWhenIdExistTest() throws Exception{
        when(getClientService.findById(1L)).thenReturn(clients.get(0));
        mockMvc.perform(get("/clients/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name", is("Client 1")))
                .andExpect(jsonPath("$.age", is(20)))
                .andExpect(jsonPath("$.telephone", is("11111111111")))
                .andExpect(jsonPath("$.email", is("blabla@blabla.com")))
                .andExpect(jsonPath("$.gender", is("Male")));
    }

    @Test
    void findWhenIdDontExist() throws Exception{
        when(getClientService.findById(3L)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(get("/clients/3"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void saveTest() throws Exception{
        ClientDTO clientDTO = new ClientDTO
                ("new client", 50, "12345678901",
                        "testeteste@hotmail.com","Female");
        when(postClientService.save(any())).thenReturn(clientDTO);
        String json = mapper.writeValueAsString(clientDTO);
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.name", is("new client")))
                .andExpect(jsonPath("$.age", is(50)))
                .andExpect(jsonPath("$.telephone", is("12345678901")))
                .andExpect(jsonPath("$.email", is("testeteste@hotmail.com")))
                .andExpect(jsonPath("$.gender", is("Female")));
    }

    @Test
    void saveWhenBodyIsInvalid() throws Exception{
        ClientDTO clientDTO = new ClientDTO
                (null, 80, "12345678901",
                        "testeteste@hotmail.com","Female");

        String json = mapper.writeValueAsString(clientDTO);
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$", is("name cannot be null or void")));
    }

    @Test
    void updateTest() throws Exception{
        ClientDTO clientDTO = new ClientDTO
                ("update", 50, "12345678901",
                        "teste@hotmail.com","Female");
        when(putClientService.update(anyLong(),any())).thenReturn(clientDTO);
        String json = mapper.writeValueAsString(clientDTO);
        mockMvc.perform(put("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name", is("update")))
                .andExpect(jsonPath("$.age", is(50)))
                .andExpect(jsonPath("$.telephone", is("12345678901")))
                .andExpect(jsonPath("$.email", is("teste@hotmail.com")))
                .andExpect(jsonPath("$.gender", is("Female")));
    }

    @Test
    void updateWhenBodyIsInvalid() throws Exception{
        ClientDTO clientDTO = new ClientDTO
                ("test name", 121, "12345678901",
                        "teste@hotmail.com","Male");

        String json = mapper.writeValueAsString(clientDTO);
        mockMvc.perform(put("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$", is("age cannot be higher than 120")));
    }

    @Test
    void deleteTest() throws Exception{
        mockMvc.perform(delete("/clients/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}