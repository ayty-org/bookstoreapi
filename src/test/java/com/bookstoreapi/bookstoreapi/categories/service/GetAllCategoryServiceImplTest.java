package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetAllCategoryServiceImplTest {
//
//    @InjectMocks
//    private GetAllCategoryServiceImpl getAllCategoryService;
//    @Mock
//    private CategoryRepository repository;
//    private final List<Category> allCategories = new LinkedList<>();
//
//
//    @BeforeEach
//    void setUp(){
//        Category category1 = new Category();
//        category1.setId(1L);
//        category1.setName("Action");
//
//        Category category2 = new Category();
//        category2.setId(2L);
//        category2.setName("Romance");
//
//        Category category3 = new Category();
//        category3.setId(3L);
//        category3.setName("Comedy");
//
//        allCategories.add(category1);
//        allCategories.add(category2);
//        allCategories.add(category3);
//    }
//
//    @Test
//    void findAllTest(){
//        when(repository.findAll()).thenReturn(allCategories);
//        List<CategoryDTO> listReturned = getAllCategoryService.findAll();
//        assertThat(3, is(equalTo(listReturned.size())));
//        for(int k = 0; k<3; k++){
//            assertThat(allCategories.get(k).getName(), is(equalTo(listReturned.get(k).getName())));
//        }
//    }
}