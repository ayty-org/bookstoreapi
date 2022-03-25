package com.bookstoreapi.bookstoreapi.categories;

import javax.persistence.*;

@Entity
public class Category {

    @Column(table = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

}
