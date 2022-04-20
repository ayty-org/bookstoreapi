package com.bookstoreapi.bookstoreapi.categories.service;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.exception.CategoryNotFoundException;

@FunctionalInterface
public interface GetCategoryService {

    Category findById(Long id) throws CategoryNotFoundException;
}
