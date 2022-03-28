package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BookFieldsVerification {

    @Autowired
    private CategoryService categoryService;


    public void bookFieldsVerification(Book book){
        titleIsValid(book);
        synopsisIsValid(book);
        isbnIsValid(book);
        publicationYearIsValid(book);
        priceIsValid(book);
        quantityInStockIsValid(book);
        authorNameIsValid(book);
        categoriesAreValid(book);
    }

    private void titleIsValid(Book book){
        if(book.getTitle() != null && book.getTitle().length() <= 60){
            String titleUpdated = book.getTitle().substring(0, 1).toUpperCase() +
                    book.getTitle().substring(1);
            book.setTitle(titleUpdated);
        }else{
            throw new IllegalArgumentException("title invalid " +
                    "(the number of characters must be between 3 and 60)");
        }
    }

    private void synopsisIsValid(Book book){
        if(book.getSynopsis() != null && book.getSynopsis().length() <= 500){
            String synopsisUpdated = book.getSynopsis().substring(0, 1).toUpperCase() +
                    book.getSynopsis().substring(1);
            book.setTitle(synopsisUpdated);
        }else{
            throw new IllegalArgumentException("synopsis invalid " +
                    "(the number of characters must be between 3 and 500)");
        }
    }

    private void isbnIsValid(Book book){
        if(book.getIsbn() != null && book.getIsbn().length() == 13){
            try{
                String isbn = book.getIsbn();
                Double.parseDouble(isbn);
                String formatedISBN = isbn.substring(0,3)+"-"+isbn.substring(3,5)+"-"+isbn.substring(5,8)+
                        "-"+isbn.substring(8,12)+"-"+isbn.substring(12);
                book.setIsbn(formatedISBN);
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("isbn invalid (must contain only DIGITS)");
            }
        }else{
            throw new IllegalArgumentException("isbn invalid (must contain 13 digits)");
        }
    }

    private void publicationYearIsValid(Book book){
        if(book.getPublicationYear() != null){
            Date bookDate = book.getPublicationYear();
            if(bookDate.after(new Date())){
                throw new IllegalArgumentException("publication year invalid " +
                        "(the release date cannot be later than the current time)");
            }
        }else{
            throw new IllegalArgumentException("publication year invalid (cannot be null)");
        }
    }

    private void priceIsValid(Book book){
        if(book.getPrice() <= 0){
            throw new IllegalArgumentException("price invalid (cannot be less than or equal to zero)");
        }
    }

    private void quantityInStockIsValid(Book book){
        if(book.getQuantityInStock() < 0){
            throw new IllegalArgumentException("quantity in stock invalid (cannot be less than zero)");
        }
    }

    private void authorNameIsValid(Book book){
        if(book.getAuthorName() == null || book.getAuthorName().length() == 0) {
            throw new IllegalArgumentException("author name invalid (cannot be null or void)");
        }else if(book.getAuthorName().length() > 60){
            throw new IllegalArgumentException("author name invalid " +
                    "(the number of characters must be between 2 and 60)");
        }
    }

    private void categoriesAreValid(Book book){
        for(Category category: book.getCategories()){
            categoryService.findById(category.getId());
        }
    }
}
