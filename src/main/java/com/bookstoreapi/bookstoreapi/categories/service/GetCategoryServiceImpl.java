package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCategoryServiceImpl implements GetCategoryService{

    @Autowired
    private CategoryService categoryService;

    @Override
    public CategoryDTO findById(Long id){
        return new CategoryDTO(categoryService.findById(id));
    }
}
