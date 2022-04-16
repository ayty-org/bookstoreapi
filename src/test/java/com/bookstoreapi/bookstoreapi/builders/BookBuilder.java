package com.bookstoreapi.bookstoreapi.builders;

import com.bookstoreapi.bookstoreapi.book.Book;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BookBuilder {

    public static Book book1L(){
        return Book.builder()
                .id(1L)
                .categories(CategoryBuilder.categoryList())
                .title("JavaScript")
                .synopsis("Aprenda JavaScript")
                .isbn("9788533302273")
                .publicationYear(new Date(14032001))
                .price(50.00)
                .quantityInStock(23)
                .authorName("JN Papo")
                .build();
    }

    public static Book book2L(){
        return Book.builder()
                .id(2L)
                .categories(CategoryBuilder.categoryList())
                .title("Angular JS")
                .synopsis("Aprenda a primeira versão do Angular")
                .isbn("9788533302273")
                .publicationYear(new Date(15042000))
                .price(80.00)
                .quantityInStock(4)
                .authorName("Gu Gou")
                .build();
    }

    public static Book book3L(){
        return Book.builder()
                .id(3L)
                .categories(CategoryBuilder.categoryList())
                .title("Algoritmos")
                .synopsis("Entenda lógica de programação")
                .isbn("9788533302273")
                .publicationYear(new Date(30042000))
                .price(100.00)
                .quantityInStock(23)
                .authorName("JN Papo")
                .build();
    }

    public static List<Book> bookList(){
        List<Book> books = new LinkedList<>();
        books.add(book1L());
        books.add(book2L());
        books.add(book3L());
        return books;
    }
}
