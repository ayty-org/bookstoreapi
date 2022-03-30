package com.bookstoreapi.bookstoreapi.book;

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
    private List<String> categoriesName;
}
