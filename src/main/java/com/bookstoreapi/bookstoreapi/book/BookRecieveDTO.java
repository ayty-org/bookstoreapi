package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRecieveDTO {

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

    @Min(value = 0, message = "price cannot be negative")
    @NotNull(message = "price cannot be null")
    private Double price;

    @Min(value = 0, message = "quantity in stock cannot be negative")
    @NotNull(message = "quantity in stock cannot be null")
    private Integer quantityInStock;

    @Size(min = 2, max = 60, message = "author name must be between 2 and 60 characters")
    @NotBlank(message = "author name cannot be null or void")
    private String authorName;

    @NotNull(message = "the book must contain at least one category")
    private List<Long> categories;

    public static Book to (BookRecieveDTO book){
        List<Category> categories = new ArrayList<>();
        for(Long id: book.getCategories()){
            categories.add(Category.builder().id(id).build());
        }
        return Book.builder()
                .title(book.getTitle())
                .synopsis(book.getSynopsis())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .price(book.getPrice())
                .categories(categories)
                .quantityInStock(book.getQuantityInStock())
                .authorName(book.getAuthorName())
                .build();
    }
}
