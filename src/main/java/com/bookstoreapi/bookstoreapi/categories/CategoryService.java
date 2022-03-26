package com.bookstoreapi.bookstoreapi.categories;

import com.bookstoreapi.bookstoreapi.user.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> findAll(){
        return categoryRepository.findAll();

    }

    public Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("Category with id "+id+" not found");
        });
    }

    public Category findByName(String name){
        return categoryRepository.findByName(name).orElseThrow( () ->{
            throw new EntityNotFoundException("Category with name "+name+" not found");
        });
    }
}
