package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.purchase.service.*;
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
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetAllPurchaseService getAllPurchaseService;
    @MockBean
    private GetPurchaseService getPurchaseService;
    @MockBean
    private PostPurchaseService postPurchaseService;
    @MockBean
    private PutPurchaseService putPurchaseService;
    @MockBean
    private DeletePurchaseService deletePurchaseService;

    ObjectMapper mapper = new ObjectMapper();

    private final List<PurchaseDTO> purchases = new ArrayList<>();


    @BeforeEach
    void setUp() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Client 1");
        Client client2 = new Client();
        client2.setName("Client 2");
        client2.setId(2L);

        Book book25 = new Book();
        book25.setId(1L);
        book25.setPrice(25.0);
        Book book50 = new Book();
        book50.setId(2L);
        book50.setPrice(50.0);
        Book book100 = new Book();
        book100.setId(3L);
        book100.setPrice(100.0);

        PurchaseDTO purchase1 = new PurchaseDTO();
        purchase1.setClient(client1);
        purchase1.setPurchasedBooks(List.of(book25,book50,book100));
        purchase1.setCompleted(true);
        purchase1.setPurchaseDate(new Date());

        PurchaseDTO purchase2 = new PurchaseDTO();
        purchase2.setClient(client2);
        purchase2.setPurchasedBooks(List.of(book100, book100));
        purchase2.setCompleted(false);
        purchase2.setPurchaseDate(new Date());

        purchases.add(purchase1);
        purchases.add(purchase2);
    }

    @Test
    void listTest() throws Exception {
        when(getAllPurchaseService.findAll()).thenReturn(purchases);
        mockMvc.perform(get("/purchases"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].client.name", is("Client 1")))
                .andExpect(jsonPath("$[0].purchasedBooks[0].price", is(25.0)))
                .andExpect(jsonPath("$[0].completed", is(true)))
                .andExpect(jsonPath("$[1].client.name", is("Client 2")))
                .andExpect(jsonPath("$[1].purchasedBooks[0].price", is(100.0)))
                .andExpect(jsonPath("$[1].completed", is(false)));


    }


    @Test
    void findWhenIdExistTest() throws Exception {
        when(getPurchaseService.findById(1L)).thenReturn(purchases.get(0));
        mockMvc.perform(get("/purchases/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.client.name", is("Client 1")))
                .andExpect(jsonPath("$.purchasedBooks[0].price", is(25.0)))
                .andExpect(jsonPath("$.completed", is(true)));
    }

    @Test
    void findWhenIdDontExist() throws Exception {
        when(getPurchaseService.findById(3L)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(get("/purchases/3"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void saveTest() throws Exception {
        PurchaseDTO purchaseDTO = purchases.get(0);
        when(postPurchaseService.save(any())).thenReturn(purchaseDTO);
        String json = mapper.writeValueAsString(purchases.get(0));
        mockMvc.perform(post("/purchases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.client.name", is("Client 1")))
                .andExpect(jsonPath("$.purchasedBooks[0].price", is(25.0)))
                .andExpect(jsonPath("$.completed", is(true)));
    }

    @Test
    void saveWhenBodyIsInvalid() throws Exception {
        PurchaseDTO purchaseDTO = purchases.get(1);
        purchaseDTO.setClient(null);

        String json = mapper.writeValueAsString(purchaseDTO);
        mockMvc.perform(post("/purchases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$", is("purchase client cannot be null")));
    }

    @Test
    void updateTest() throws Exception {
        when(putPurchaseService.update(anyLong(), any())).thenReturn(purchases.get(1));
        String json = mapper.writeValueAsString(purchases.get(1));
        mockMvc.perform(put("/purchases/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.client.name", is("Client 2")))
                .andExpect(jsonPath("$.purchasedBooks[0].price", is(100.0)))
                .andExpect(jsonPath("$.completed", is(false)));
    }

    @Test
    void updateWhenBodyIsInvalid() throws Exception {
        PurchaseDTO purchaseDTO = purchases.get(1);
        purchaseDTO.setPurchasedBooks(null);

        String json = mapper.writeValueAsString(purchaseDTO);
        mockMvc.perform(put("/purchases/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$", is("a purchase must have at least one book")));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/purchases/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        verify(deletePurchaseService).delete(1L);
    }

    @Test
    void deleteWhenIdDontExist() throws Exception {
        when(mockMvc.perform(delete("/purchases/1"))).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(delete("/purchases/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        verify(deletePurchaseService).delete(1L);
    }

}