package com.bookstoreapi.bookstoreapi.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/categories")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public List<Category> list(){
        return categoryService.findAll();
    }

    @GetMapping("/{categoryId}")
    public Category find(@PathVariable Long categoryId){
        return categoryService.findById(categoryId);
    }
}
