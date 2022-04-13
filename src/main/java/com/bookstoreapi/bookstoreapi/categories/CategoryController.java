package com.bookstoreapi.bookstoreapi.categories;

import com.bookstoreapi.bookstoreapi.categories.service.GetAllCategoryService;
import com.bookstoreapi.bookstoreapi.categories.service.GetCategoryService;
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
    public List<CategoryDTO> list(){
        return getAllCategoryService.findAll();
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO find(@PathVariable Long categoryId){
        return getCategoryService.findById(categoryId);
    }
}
