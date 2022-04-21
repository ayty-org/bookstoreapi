package com.bookstoreapi.bookstoreapi.categories;

import com.bookstoreapi.bookstoreapi.categories.service.GetAllCategoryService;
import com.bookstoreapi.bookstoreapi.categories.service.GetCategoryService;
import com.bookstoreapi.bookstoreapi.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final GetAllCategoryService getAllCategoryService;
    private final GetCategoryService getCategoryService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> list() {
        return CategoryDTO.fromAll(getAllCategoryService.findAll());
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO find(@PathVariable Long categoryId) throws CategoryNotFoundException {
        return CategoryDTO.from(getCategoryService.findById(categoryId));
    }
}
