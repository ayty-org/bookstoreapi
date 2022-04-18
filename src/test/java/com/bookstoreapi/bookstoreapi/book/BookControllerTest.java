package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.BookstoreApiJacksonApplicationTests;
import com.bookstoreapi.bookstoreapi.builders.BookBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class BookControllerTest extends BookstoreApiJacksonApplicationTests {

    private MockMvc mockMvc;
    @Autowired
    private BookController bookController;
    ObjectMapper mapper = new ObjectMapper();
    private String url = "/books";


    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void saveTest() throws Exception{
        String json = mapper.writeValueAsString(BookRecieveDTO.from(BookBuilder.book1L()));

        this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

    }
}