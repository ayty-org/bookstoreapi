package com.bookstoreapi.bookstoreapi.client;

import com.bookstoreapi.bookstoreapi.BookstoreApiJacksonApplicationTests;
import com.bookstoreapi.bookstoreapi.builders.ClientBuilder;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
public class ClientControllerTest extends BookstoreApiJacksonApplicationTests {


    private MockMvc mockMvc;
    @Autowired
    private ClientController clientController;
    @Autowired
    private PurchaseRepository purchaseRepository;


    @Mock
    private ClientRepository repository;
    private final String url = "/clients";
    ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void saveTest() throws Exception {
        Client c1 = ClientBuilder.clientJenipapo1();
        Client c2 = ClientBuilder.clientAna2();

        String json1 = mapper.writeValueAsString(ClientDTO.from(c1));
        String json2 = mapper.writeValueAsString(ClientDTO.from(c2));

        this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Jenipapo")))
                .andExpect(jsonPath("$.age", is(19)))
                .andExpect(jsonPath("$.email", is("jenipapo@coldmail.com")))
                .andExpect(jsonPath("$.telephone", is("83996438691")))
                .andExpect(jsonPath("$.gender", is("Male")));

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json2))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Ana")))
                .andExpect(jsonPath("$.age", is(46)))
                .andExpect(jsonPath("$.email", is("ana@coldmail.com")))
                .andExpect(jsonPath("$.telephone", is("83996438691")))
                .andExpect(jsonPath("$.gender", is("Female")));
    }

    @Test
    void saveWhenClientIsInvalidTest() throws Exception {
        Client c1 = ClientBuilder.clientInvalid();
        String json1 = mapper.writeValueAsString(ClientDTO.from(c1));

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isBadRequest());
    }



    @Test
    void getAllTest() throws Exception{
        this.repository.deleteAll();
        this.repository.save(ClientBuilder.clientJenipapo1());
        this.repository.save(ClientBuilder.clientAna2());

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Jenipapo")))
                .andExpect(jsonPath("$[0].age", is(19)))
                .andExpect(jsonPath("$[0].email", is("jenipapo@coldmail.com")))
                .andExpect(jsonPath("$[0].telephone", is("83996438691")))
                .andExpect(jsonPath("[0].gender", is("Male")))
                .andExpect(jsonPath("$[1].name", is("Ana")))
                .andExpect(jsonPath("$[1].age", is(46)))
                .andExpect(jsonPath("[1].email", is("ana@coldmail.com")))
                .andExpect(jsonPath("$[1].telephone", is("83996438691")))
                .andExpect(jsonPath("$[1].gender", is("Female")));
    }

    @Test
    void getOneTest() throws Exception{
        mockMvc.perform(get(url+"/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Jenipapo")))
                .andExpect(jsonPath("$.age", is(19)))
                .andExpect(jsonPath("$.email", is("jenipapo@coldmail.com")))
                .andExpect(jsonPath("$.telephone", is("83996438691")))
                .andExpect(jsonPath("$.gender", is("Male")));
    }

    @Test
    void getWhenDontExistTest() {
        Assertions.assertThatThrownBy(() ->
                        mockMvc.perform(get(url + "/10"))
                                .andExpect(status().isNotFound()))
                .hasMessageContaining("Client with id 10 not found");
    }

    @Test
    void putWhenIdExistTest() throws Exception{
        Client c1 = ClientBuilder.clientJenipapo1();
        c1.setName("Newmar");
        c1.setAge(44);
        c1.setEmail("newmar@gmail.com");

        String json1 = mapper.writeValueAsString(ClientDTO.from(c1));
        mockMvc.perform(put(url+"/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name", is("Newmar")))
                .andExpect(jsonPath("$.age", is(44)))
                .andExpect(jsonPath("$.email", is("newmar@gmail.com")))
                .andExpect(jsonPath("$.telephone", is("83996438691")))
                .andExpect(jsonPath("$.gender", is("Male")));
    }

    @Test
    void putWhenIdDontExistTest() throws Exception{
        Client c1 = ClientBuilder.clientJenipapo1();
        String json1 = mapper.writeValueAsString(ClientDTO.from(c1));
        Assertions.assertThatThrownBy(() ->mockMvc.perform(put(url+"/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(MockMvcResultMatchers.status().isNotFound()))
                .hasMessageContaining("Client with id 10 not found");
    }

    @Test
    void putWhenClientIsInvalid() throws Exception{
        Client c1 = ClientBuilder.clientInvalid();
        String json1 = mapper.writeValueAsString(ClientDTO.from(c1));

        mockMvc.perform(put(url+"/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteWhenIdExist() throws Exception{
        mockMvc.perform(delete(url+"/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Assertions.assertThatThrownBy(() -> mockMvc.perform(get(url+"/4"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()));
    }

    @Test
    void deleteWhenIdDontExist() throws Exception{
        Assertions.assertThatThrownBy(() ->mockMvc.perform(delete(url+"/4"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(jsonPath("$", is("Client with id 4 not found"))));
    }
}