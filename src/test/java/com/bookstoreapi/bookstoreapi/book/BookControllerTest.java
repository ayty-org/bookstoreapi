package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.BookstoreApiBooksTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-books-test.properties")
public class BookControllerTest extends BookstoreApiBooksTests {


    private MockMvc mockMvc;
    @Autowired
    private BookController bookController;

    @BeforeEach
    void setUp(){
        this.mockMvc =  this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void saveTest(){

    }



}