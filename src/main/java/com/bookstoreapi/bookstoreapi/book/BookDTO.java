package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {


    @Size(min = 2, max = 60, message = "title must be between 2 and 60 characters")
    @NotBlank(message = "title cannot be null or void")
    private String title;

    @Size(min = 2, max = 500, message = "synopsis must be between 2 and 500 characters")
    @NotBlank(message = "synopsis cannot be null or void")
    private String synopsis;

    @ISBN(message = "invalid ISBN")
    @NotBlank(message = "isbn cannot be null or void")
    private String isbn;

    @NotNull(message = "publication year cannot be null")
    private Date publicationYear;

    @NotNull(message = "price cannot be null")
    @Min(value = 0, message = "price cannot be negative")
    private double price;


    @Min(value = 0, message = "quantity in stock cannot be negative")
    @NotNull(message = "quantity in stock cannot be null")
    private int quantityInStock;

    @Size(min = 2, max = 60, message = "author name must be between 2 and 60 characters")
    @NotBlank(message = "author name cannot be null or void")
    private String authorName;

    @NotNull(message = "the book must contain at least one category")
    private List<Category> categories;

    public BookDTO(Book book){
        this.title = book.getTitle();
        this.synopsis = book.getSynopsis();
        this.isbn = book.getIsbn();
        this.publicationYear = book.getPublicationYear();
        this.price = book.getPrice();
        this.quantityInStock = book.getQuantityInStock();
        this.authorName = book.getAuthorName();
        this.categories = book.getCategories();
    }
}
