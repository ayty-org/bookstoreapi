package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;

import java.util.List;

@FunctionalInterface
public interface GetAllCategoryService {

    List<CategoryDTO> findAll();
}
