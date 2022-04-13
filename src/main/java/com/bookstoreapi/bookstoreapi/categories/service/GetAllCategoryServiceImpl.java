package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetAllCategoryServiceImpl implements GetAllCategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll(){
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::new).collect(Collectors.toList());

    }
}
