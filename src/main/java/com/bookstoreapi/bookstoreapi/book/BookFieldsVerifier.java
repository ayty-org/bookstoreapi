package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BookFieldsVerifier {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookFieldsFormatter bookFieldsFormatter;


    public void bookFieldsVerification(Book book){
        titleIsValid(book);
        synopsisIsValid(book);
        isbnIsValid(book);
        publicationYearIsValid(book);
        priceIsValid(book);
        quantityInStockIsValid(book);
        authorNameIsValid(book);
        categoriesAreValid(book);

        bookFieldsFormatter.fieldsFormatter(book);
    }

    private void titleIsValid(Book book){
        if(book.getTitle() == null || book.getTitle().length() > 60){
            throw new IllegalArgumentException("title invalid " +
                    "(the number of characters must be between 3 and 60)");
        }
    }

    private void synopsisIsValid(Book book){
        if(book.getSynopsis() == null || book.getSynopsis().length() > 500){
            throw new IllegalArgumentException("synopsis invalid " +
                    "(the number of characters must be between 3 and 500)");
        }
    }

    private void isbnIsValid(Book book){
        if(book.getIsbn() != null && book.getIsbn().length() == 13){
            try{
                Double.parseDouble(book.getIsbn());
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
