package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    private UUID uuid;
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
                .uuid(book.getUuid())
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

    public static List<BookDTO> fromAll (List<Book> books) {
        return books.stream()
                .map(BookDTO::from)
                .collect(Collectors.toList());
    }
}
