package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCategoryServiceImpl implements GetCategoryService{

    private final CategoryService categoryService;

    @Override
    public CategoryDTO findById(Long id){
        return new CategoryDTO(categoryService.findById(id));
    }
}
