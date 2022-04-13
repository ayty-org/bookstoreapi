package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
