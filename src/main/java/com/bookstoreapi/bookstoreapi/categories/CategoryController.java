package com.bookstoreapi.bookstoreapi.categories;

import com.bookstoreapi.bookstoreapi.categories.service.GetAllCategoryService;
import com.bookstoreapi.bookstoreapi.categories.service.GetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/categories")
@RestController
public class CategoryController {

    @Autowired
    private GetAllCategoryService getAllCategoryService;
    @Autowired
    private GetCategoryService getCategoryService;


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
