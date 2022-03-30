package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    private String title;
    private String synopsis;
    private String isbn;
    private Date publicationYear;
    private double price;
    private String authorName;
    private List<Category> categories;

    public BookDTO(Book book){
        this.title = book.getTitle();
        this.synopsis = book.getSynopsis();
        this.isbn = book.getIsbn();
        this.publicationYear = book.getPublicationYear();
        this.price = book.getPrice();
        this.authorName = book.getAuthorName();
        this.categories = book.getCategories();
    }
}
