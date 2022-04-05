package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class GetCategoryServiceImplTest {


    @InjectMocks
    private GetCategoryServiceImpl getCategoryService;
    @Mock
    private CategoryService service;
    Map<Long, Category> categories = new HashMap<>();


    @BeforeEach
    void setUp(){
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Romance");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Comedy");

        categories.put(1L, category1);
        categories.put(2L, category2);
    }

    @Test
    void testGetByIdWhenIdExist(){
        when(service.findById(1L)).thenReturn(categories.get(1L));
        when(service.findById(2L)).thenReturn(categories.get(2L));

        CategoryDTO categoryDTO = getCategoryService.findById(1L);
        assertThat("Romance", is(equalTo(categoryDTO.getName())));
        categoryDTO = getCategoryService.findById(2L);
        assertThat("Comedy", is(equalTo(categoryDTO.getName())));
    }

    @Test
    void testGetByIdWhenIdDontExist(){
        when(service.findById(3L)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, ()-> getCategoryService.findById(3L));
    }
}