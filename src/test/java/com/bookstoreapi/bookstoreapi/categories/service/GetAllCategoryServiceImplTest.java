package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.builders.CategoryBuilder;
import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class GetAllCategoryServiceImplTest {

    private GetAllCategoryServiceImpl getAllCategoryService;
    @Mock
    private CategoryRepository repository;


    @BeforeEach
    void setUp(){
        this.getAllCategoryService = new GetAllCategoryServiceImpl(repository);
    }

    @Test
    void findAllTest(){
        when(repository.findAll()).thenReturn(CategoryBuilder.categoryList());

        List<Category> categories = getAllCategoryService.findAll();

        assertThat(3, is(categories.size()));
        verify(repository, times(1)).findAll();

        assertThat(1L, is(categories.get(0).getId()));
        assertThat("Romance", is(categories.get(0).getName()));

        assertThat(2L, is(categories.get(1).getId()));
        assertThat("Comedy", is(categories.get(1).getName()));

        assertThat(3L, is(categories.get(2).getId()));
        assertThat("Adventure", is(categories.get(2).getName()));
    }
}