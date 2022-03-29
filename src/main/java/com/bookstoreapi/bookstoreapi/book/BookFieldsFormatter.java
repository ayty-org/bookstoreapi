package com.bookstoreapi.bookstoreapi.book;

import org.springframework.stereotype.Component;

@Component
public class BookFieldsFormatter {


    public void fieldsFormatter(Book book){
       titleFormatter(book);
       isbnFormatter(book);
    }

    private void titleFormatter(Book book){
        String titleUpdated = book.getTitle().substring(0, 1).toUpperCase() +
                book.getTitle().substring(1);
        book.setTitle(titleUpdated);
    }

    private void isbnFormatter(Book book){
        String isbn = book.getIsbn();
        String formatedISBN = isbn.substring(0,3)+"-"+isbn.substring(3,5)+"-"+isbn.substring(5,8)+
                "-"+isbn.substring(8,12)+"-"+isbn.substring(12);
        book.setIsbn(formatedISBN);
    }

}
