package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository repository;
    private Category category;

    @BeforeEach
    void setUp(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Romance");
        this.category = category;
    }

    @Test
    void findByIdWhenIdExistTest() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(category));

        Category categorySaved = categoryService.findById(1L);
        assertThat("Romance", is(categorySaved.getName()));
    }

    @Test
    void findByIdWhenIdDontExist() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> categoryService.findById(1L));
    }
}