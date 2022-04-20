package com.bookstoreapi.bookstoreapi.book.service.abstracts;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import com.bookstoreapi.bookstoreapi.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class GetCategoriesAbstract extends FindByUuidBookAbstract {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategoriesByUuid(List<Category> categories) throws CategoryNotFoundException {
        List<Category> categoriesSaved = new ArrayList<>();
        for (Category category : categories) {
            categoriesSaved.add(categoryRepository.findById(category.getId())
                    .orElseThrow(() -> new CategoryNotFoundException(category.getId())));
        }
        return categoriesSaved;
    }
}
