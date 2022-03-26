package com.bookstoreapi.bookstoreapi.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
