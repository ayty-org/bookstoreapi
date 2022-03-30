package com.bookstoreapi.bookstoreapi.categories;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    public Category(CategoryDTO categoryDTO){
        this.name = categoryDTO.getName();
    }

}
