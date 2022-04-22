package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.BookstoreApiJacksonApplicationTests;
import com.bookstoreapi.bookstoreapi.builders.BookBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class BookControllerTest extends BookstoreApiJacksonApplicationTests {

    private MockMvc mockMvc;
    @Autowired
    private BookController bookController;
    ObjectMapper mapper = new ObjectMapper();
    private final String url = "/books";


    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void saveTest() throws Exception{
        String json = mapper.writeValueAsString(BookBuilder.book1LBookRecieve());

        this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("JavaScript")))
                .andExpect(jsonPath("$.categories[0].name", is("Romance")))
                .andExpect(jsonPath("$.synopsis", is("Aprenda JavaScript")))
                .andExpect(jsonPath("$.isbn", is("9788533302273")))
                .andExpect(jsonPath("$.price", is(50.00)))
                .andExpect(jsonPath("$.quantityInStock", is(23)))
                .andExpect(jsonPath("$.authorName", is("JN Papo")));
    }

    @Test
    void saveWhenBookIsInvalid() throws Exception{
        String json = mapper.writeValueAsString(BookBuilder.bookInvalid());

        this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllTest() throws Exception{
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$[0].title", is("JavaScript")))
                .andExpect(jsonPath("$[0].categories[0].name", is("Romance")))
                .andExpect(jsonPath("$[0].synopsis", is("Aprenda JavaScript")))
                .andExpect(jsonPath("$[0].isbn", is("9788533302273")))
                .andExpect(jsonPath("$[0].price", is(50.00)))
                .andExpect(jsonPath("$[0].quantityInStock", is(23)))
                .andExpect(jsonPath("$[0].authorName", is("JN Papo")))

                .andExpect(jsonPath("$[1].uuid", is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .andExpect(jsonPath("$[1].title", is("Angular JS")))
                .andExpect(jsonPath("$[1].categories[1].name", is("Action")))
                .andExpect(jsonPath("$[1].synopsis", is("Aprenda a primeira versão do Angular")))
                .andExpect(jsonPath("$[1].isbn", is("9788533302273")))
                .andExpect(jsonPath("$[1].price", is(80.00)))
                .andExpect(jsonPath("$[1].quantityInStock", is(4)))
                .andExpect(jsonPath("$[1].authorName", is("Gu Gou")));
    }

    @Test
    void getTest() throws Exception{
        this.mockMvc.perform(get(url+"/12d51c0a-a843-46fc-8447-5fda559ec69b"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$.title", is("JavaScript")))
                .andExpect(jsonPath("$.categories[0].name", is("Romance")))
                .andExpect(jsonPath("$.synopsis", is("Aprenda JavaScript")))
                .andExpect(jsonPath("$.isbn", is("9788533302273")))
                .andExpect(jsonPath("$.price", is(50.00)))
                .andExpect(jsonPath("$.quantityInStock", is(23)))
                .andExpect(jsonPath("$.authorName", is("JN Papo")));
    }

    @Test
    void getAllByCategoryNameTest() throws Exception{
        this.mockMvc.perform(get(url+"/categories/Romance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$[0].title", is("JavaScript")))
                .andExpect(jsonPath("$[0].categories[0].name", is("Romance")))
                .andExpect(jsonPath("$[0].synopsis", is("Aprenda JavaScript")))
                .andExpect(jsonPath("$[0].isbn", is("9788533302273")))
                .andExpect(jsonPath("$[0].price", is(50.00)))
                .andExpect(jsonPath("$[0].quantityInStock", is(23)))
                .andExpect(jsonPath("$[0].authorName", is("JN Papo")))

                .andExpect(jsonPath("$[1].uuid", is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .andExpect(jsonPath("$[1].title", is("Angular JS")))
                .andExpect(jsonPath("$[1].categories[1].name", is("Action")))
                .andExpect(jsonPath("$[1].synopsis", is("Aprenda a primeira versão do Angular")))
                .andExpect(jsonPath("$[1].isbn", is("9788533302273")))
                .andExpect(jsonPath("$[1].price", is(80.00)))
                .andExpect(jsonPath("$[1].quantityInStock", is(4)))
                .andExpect(jsonPath("$[1].authorName", is("Gu Gou")));
    }

    @Test
    void updateTest() throws Exception{

        BookRecieveDTO bookUpdated = BookBuilder.book1LBookRecieve();
        bookUpdated.setAuthorName("updated da silva");
        bookUpdated.setTitle("updated");
        String json = mapper.writeValueAsString(bookUpdated);

        mockMvc.perform(put(url+"/27eaa649-e8fa-4889-bd5a-ea6825b71e62")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is("27eaa649-e8fa-4889-bd5a-ea6825b71e62")))
                .andExpect(jsonPath("$.title", is("updated")))
                .andExpect(jsonPath("$.categories[0].name", is("Romance")))
                .andExpect(jsonPath("$.synopsis", is("Aprenda JavaScript")))
                .andExpect(jsonPath("$.isbn", is("9788533302273")))
                .andExpect(jsonPath("$.price", is(50.00)))
                .andExpect(jsonPath("$.quantityInStock", is(23)))
                .andExpect(jsonPath("$.authorName", is("updated da silva")));
    }

    @Test
    void updateWhenIdDontExistTest() throws Exception{
        String json = mapper.writeValueAsString(BookBuilder.book1LBookRecieve());

        Assertions.assertThatThrownBy(() -> mockMvc.perform(
                put(url+"/27eaa649-e8fa-4889-bd5a-ea6825b71e4b")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest()))
                .hasMessageContaining("Book with id 27eaa649-e8fa-4889-bd5a-ea6825b71e4b not found");
    }

    @Test
    void deleteTest() throws Exception{
        mockMvc.perform(delete(url+"/27eaa649-e8fa-4889-bd5a-ea6825b71e62"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Assertions.assertThatThrownBy(() -> mockMvc.perform(get(url+"/27eaa649-e8fa-4889-bd5a-ea6825b71e62"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()));
    }

    @Test
    void deleteWhenIdDontExist(){
        Assertions.assertThatThrownBy(() ->mockMvc.perform(delete(url+"/27eaa649-e8fa-4889-bd5a-ea6825b71ea6"))
                        .andExpect(MockMvcResultMatchers.status().isNotFound()))
                .hasMessageContaining("Book with id 27eaa649-e8fa-4889-bd5a-ea6825b71ea6 not found");
    }

    @Test
    void deleteWhenExistPurchaseWithBook() {
        Assertions.assertThatThrownBy(() ->mockMvc.perform(delete(url+"/12d51c0a-a843-46fc-8447-5fda559ec69b"))
                        .andExpect(MockMvcResultMatchers.status().isConflict()))
                .hasMessageContaining("Book with id 12d51c0a-a843-46fc-8447-5fda559ec69b" +
                        " cannot be deleted because it is in one or more purchases");
    }
}