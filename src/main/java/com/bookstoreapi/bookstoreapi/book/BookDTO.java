package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    private String title;
    private String synopsis;
    private String isbn;
    private Date publicationYear;
    private Double price;
    private Integer quantityInStock;
    private String authorName;
    private List<Category> categories;


    public static BookDTO from (Book book){
        return BookDTO.builder()
                .title(book.getTitle())
                .synopsis(book.getSynopsis())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .price(book.getPrice())
                .quantityInStock(book.getQuantityInStock())
                .authorName(book.getAuthorName())
                .categories(book.getCategories())
                .build();
    }

    public static Book to (BookRecieveDTO book){
        return Book.builder()
                .title(book.getTitle())
                .synopsis(book.getSynopsis())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .price(book.getPrice())
                .quantityInStock(book.getQuantityInStock())
                .authorName(book.getAuthorName())
                .build();
    }

    public static List<BookDTO> fromAll (List<Book> books) {
        return books.stream()
                .map(BookDTO::from)
                .collect(Collectors.toList());
    }

    public static String getClassName(){
        return "Book";
    }
}
