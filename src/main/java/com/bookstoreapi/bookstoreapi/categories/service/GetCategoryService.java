package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.Category;

@FunctionalInterface
public interface GetCategoryService {

    Category findById(Long id);
}
