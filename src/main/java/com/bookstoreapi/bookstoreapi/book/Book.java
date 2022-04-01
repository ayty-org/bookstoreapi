package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
    private double price;
    private int quantityInStock;
    private String authorName;

    @ManyToMany
    private List<Category> categories;

    public Book(BookDTO bookDTO){
        this.title = bookDTO.getTitle();
        this.synopsis = bookDTO.getSynopsis();
        this.isbn = bookDTO.getIsbn();
        this.publicationYear = bookDTO.getPublicationYear();
        this.price = bookDTO.getPrice();
        this.quantityInStock = bookDTO.getQuantityInStock();
        this.authorName = bookDTO.getAuthorName();
        this.categories = bookDTO.getCategories();
    }

}
