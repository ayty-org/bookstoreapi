package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Builder
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String title;
    private String synopsis;
    private String isbn;
    private Date publicationYear;
    private Double price;
    private Integer quantityInStock;
    private String authorName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="books_categories",
            joinColumns={
                    @JoinColumn(name = "book_id", referencedColumnName = "uuid")},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id")}
            )
    private List<Category> categories;
}
