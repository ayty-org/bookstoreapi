package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String synopsis;
    private String isbn;
    private Date publicationYear;
    private double price;
    private int quantityInStock;
    private String authorName;

    @ManyToMany
    private List<Category> categories;

}
