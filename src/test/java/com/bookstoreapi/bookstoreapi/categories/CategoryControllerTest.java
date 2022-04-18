package com.bookstoreapi.bookstoreapi.categories;

import com.bookstoreapi.bookstoreapi.BookstoreApiJacksonApplicationTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class CategoryControllerTest extends BookstoreApiJacksonApplicationTests {


    private MockMvc mockMvc;
    @Autowired
    private CategoryController categoryController;
    private String url = "/categories";


    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAll() throws Exception{
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].name", is("Romance")))
                .andExpect(jsonPath("$[1].name", is("Action")))
                .andExpect(jsonPath("$[2].name", is("Fantasy")));
    }

    @Test
    void getWhenIdExist() throws Exception{
        mockMvc.perform(get(url+"/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Romance")));

    }

    @Test
    void getWhenIdDontExist() throws Exception{
        Assertions.assertThatThrownBy(() ->
                        mockMvc.perform(get(url + "/10"))
                                .andExpect(status().isNotFound()))
                .hasMessageContaining("Category with id 10 not found");
    }
}