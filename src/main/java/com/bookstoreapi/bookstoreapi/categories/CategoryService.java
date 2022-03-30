package com.bookstoreapi.bookstoreapi.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<CategoryDTO> findAll(){
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::new).collect(Collectors.toList());

    }

    private Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("Category with id "+id+" not found");
        });
    }

    public CategoryDTO getDTO(Long id){
        return new CategoryDTO(this.findById(id));
    }

}
