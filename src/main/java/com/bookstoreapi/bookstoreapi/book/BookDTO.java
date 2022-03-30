package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookDTO {

    private String title;
    private String synopsis;
    private Date publicationYear;
    private double price;
    private String authorName;
    private List<Category> categories;
}
