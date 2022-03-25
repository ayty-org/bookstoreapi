package com.bookstoreapi.bookstoreapi.categories;

import javax.persistence.*;

@Table(name = "categories")
@Entity
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

}
