package com.bookstoreapi.bookstoreapi.categories;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private String name;

    public CategoryDTO(Category category){
        this.name = category.getName();
    }
}
