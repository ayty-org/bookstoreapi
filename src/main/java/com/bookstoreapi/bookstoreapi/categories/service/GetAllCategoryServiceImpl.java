package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllCategoryServiceImpl implements GetAllCategoryService{

    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
