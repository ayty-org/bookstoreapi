package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;

import java.util.List;

@FunctionalInterface
public interface GetAllByCategoryNameBookService {

    List<Book> findAllByCategoriesName(String name);

}
