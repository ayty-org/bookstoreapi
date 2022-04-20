package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.BookstoreApiJacksonApplicationTests;
import com.bookstoreapi.bookstoreapi.builders.PurchaseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class PurchaseControllerTest extends BookstoreApiJacksonApplicationTests {

    private MockMvc mockMvc;
    @Autowired
    private PurchaseController purchaseController;

    private final String url = "/purchases";
    ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(purchaseController).build();
    }

    @Test
    void saveTest() throws Exception{
        String json = mapper.writeValueAsString(PurchaseBuilder.purchaseRecieve());

        this.mockMvc.perform(post(this.url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.client.name", is("Jenipapo")))
                .andExpect(jsonPath("$.amount", is(230.00)))
                .andExpect(jsonPath("$.isCompleted", is(true)));
    }


    @Test
    void saveWhenPurchaseIsInvalid() throws Exception{
        PurchaseRecieveDTO p = PurchaseBuilder.purchaseRecieve();
        p.setClient(null);
        String json = mapper.writeValueAsString(p);

        this.mockMvc.perform(post(this.url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    void saveWhenClientDontExist() throws Exception {
//        Purchase p = PurchaseBuilder.purchase1L();
//        p.getClient().setId(8L);
        String json = mapper.writeValueAsString(PurchaseBuilder.purchaseRecieve());

        Assertions.assertThatThrownBy(() ->this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Client with id 8 not found");
    }

    @Test
    void saveWhenBookDontExist() throws Exception{
        Purchase p = PurchaseBuilder.purchase1L();
        //p.getPurchasedBooks().add(BookDTO.builder().uuid().build());
        String json = mapper.writeValueAsString(PurchaseBuilder.purchaseRecieve());

        Assertions.assertThatThrownBy(() ->this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Book with id 8 not found");
    }

    @Test
    void getAllTest() throws Exception{
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].client.name", is("Jenipapo")))
                .andExpect(jsonPath("$[0].amount", is(100.00)))
                .andExpect(jsonPath("$[0].isCompleted", is(true)))
                .andExpect(jsonPath("$[1].client.name", is("Ana")))
                .andExpect(jsonPath("$[1].amount", is(200.00)))
                .andExpect(jsonPath("$[1].isCompleted", is(false)));
    }

    @Test
    void getTest() throws Exception{
        mockMvc.perform(get(url+"/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client.name", is("Jenipapo")))
                .andExpect(jsonPath("$.amount", is(100.00)))
                .andExpect(jsonPath("$.isCompleted", is(true)));
    }

    @Test
    void getWhenIdDontExist() throws Exception{
        Assertions.assertThatThrownBy(() ->mockMvc.perform(get(url+"/10"))
                .andExpect(status().isNotFound()))
                .hasMessageContainingAll("Purchase with id 10 not found");
    }

    @Test
    void updateTest() throws Exception{
        PurchaseRecieveDTO purchase = PurchaseBuilder.purchaseRecieve();
        purchase.setClient(2L);
        purchase.setIsCompleted(true);

        String json = mapper.writeValueAsString(purchase);

        this.mockMvc.perform(put(this.url+"/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client.name", is("Ana")))
                .andExpect(jsonPath("$.amount", is(230.00)))
                .andExpect(jsonPath("$.isCompleted", is(true)));
    }

    @Test
    void updateWhenIdDontExist() throws Exception{
        PurchaseRecieveDTO purchase = PurchaseBuilder.purchaseRecieve();

        String json = mapper.writeValueAsString(purchase);

        Assertions.assertThatThrownBy(() ->this.mockMvc.perform(put(this.url+"/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Purchase with id 10 not found");
    }

    @Test
    void updateWhenClientDontExist() throws Exception{
        PurchaseRecieveDTO purchase = PurchaseBuilder.purchaseRecieve();
        purchase.setClient(10L);

        String json = mapper.writeValueAsString(purchase);

        Assertions.assertThatThrownBy(() ->this.mockMvc.perform(put(this.url+"/3")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Client with id 10 not found");
    }

    @Test
    void updateWhenBookDontExist() throws Exception{
        PurchaseRecieveDTO purchase = PurchaseBuilder.purchaseRecieve();
        List<Long> ids = new LinkedList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(10L);

        purchase.setPurchasedBooks(ids);
        String json = mapper.writeValueAsString(purchase);

        Assertions.assertThatThrownBy(() ->this.mockMvc.perform(put(this.url+"/3")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Book with id 10 not found");
    }

    @Test
    void deleteTest() throws Exception{
        this.mockMvc.perform(delete(this.url+"/4"))
                .andExpect(status().isNoContent());

        Assertions.assertThatThrownBy(() ->this.mockMvc.perform(get(this.url+"/4"))
                .andExpect(status().isBadRequest()));
    }

    @Test
    void deleteWhenIdDontExistTest() throws Exception{
        Assertions.assertThatThrownBy(() ->this.mockMvc.perform(delete(this.url+"/10"))
                .andExpect(status().isNotFound()))
                .hasMessageContainingAll("Purchase with id 10 not found");
    }
}