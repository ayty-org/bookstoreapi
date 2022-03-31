package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;

@FunctionalInterface
public interface GetCategoryService {

    CategoryDTO findById(Long id);
}
