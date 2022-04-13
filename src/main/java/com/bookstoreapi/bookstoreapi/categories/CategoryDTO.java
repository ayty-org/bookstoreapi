package com.bookstoreapi.bookstoreapi.categories;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Builder
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


    public static Category from(CategoryDTO categoryDTO){
        return Category.builder()
                .name(categoryDTO.getName())
                .build();
    }

    public static CategoryDTO from(Category category){
        return CategoryDTO.builder()
                .name(category.getName())
                .build();
    }

    public static List<CategoryDTO> fromAll (List<Category> clients) {
        return clients.stream()
                .map(CategoryDTO::from)
                .collect(Collectors.toList());
    }
}
