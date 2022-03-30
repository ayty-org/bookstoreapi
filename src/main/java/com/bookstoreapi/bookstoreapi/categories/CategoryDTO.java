package com.bookstoreapi.bookstoreapi.categories;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    @NotBlank
    private String name;

    public CategoryDTO(Category category){
        this.name = category.getName();
    }
}
