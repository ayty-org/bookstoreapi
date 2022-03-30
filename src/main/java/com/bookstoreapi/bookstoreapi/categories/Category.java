package com.bookstoreapi.bookstoreapi.categories;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

}
