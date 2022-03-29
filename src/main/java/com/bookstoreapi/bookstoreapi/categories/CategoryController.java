package com.bookstoreapi.bookstoreapi.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/categories")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> list(){
        return categoryService.findAll();
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public Category find(@PathVariable Long categoryId){
        return categoryService.findById(categoryId);
    }
}
