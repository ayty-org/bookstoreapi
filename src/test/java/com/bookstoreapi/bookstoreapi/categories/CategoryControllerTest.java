package com.bookstoreapi.bookstoreapi.categories;

import com.bookstoreapi.bookstoreapi.BookstoreApiJacksonApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Collections.get;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CategoryControllerTest extends BookstoreApiJacksonApplicationTests {


    private MockMvc mockMvc;
    @Autowired
    private CategoryController categoryController;
    private String url = "/categories"


    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAll(){
        mockMvc.perform(get(url))
                .andExpect("")

    }

}