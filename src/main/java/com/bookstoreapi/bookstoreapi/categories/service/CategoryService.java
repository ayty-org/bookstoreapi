package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        });
    }
}
