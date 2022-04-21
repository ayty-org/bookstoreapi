package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.builders.CategoryBuilder;
import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class GetCategoryServiceImplTest {

    private GetCategoryServiceImpl getCategoryService;
    @Mock
    private CategoryRepository repository;


    @BeforeEach
    void setUp(){
        this.getCategoryService = new GetCategoryServiceImpl(repository);
    }

    @Test
    void testGetByIdWhenIdExist() throws Exception{
      when(repository.findById(1L)).thenReturn(Optional.of(CategoryBuilder.categoryRomance()));

      Category category = getCategoryService.findById(null);

      assertThat(1L, is(category.getId()));
      assertThat("Romance", is(category.getName()));
    }

    @Test
    void testGetByIdWhenIdDontExist(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> getCategoryService.findById(3L));
    }
}