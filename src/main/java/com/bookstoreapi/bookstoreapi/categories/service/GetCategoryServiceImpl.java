package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class GetCategoryServiceImpl implements GetCategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO findById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("Category with id "+id+" not found");
        });
        return new CategoryDTO(category);
    }
}
