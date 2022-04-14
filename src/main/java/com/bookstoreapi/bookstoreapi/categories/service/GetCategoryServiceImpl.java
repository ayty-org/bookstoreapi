package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCategoryServiceImpl implements GetCategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) throws EntityNotFoundException {
        return categoryRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException(id, CategoryDTO.getClassName());
        });
    }
}
