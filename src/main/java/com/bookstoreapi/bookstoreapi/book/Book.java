package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Builder
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String synopsis;
    private String isbn;
    private Date publicationYear;
    private Double price;
    private Integer quantityInStock;
    private String authorName;

    @OneToMany
    private List<Category> categories;
}
