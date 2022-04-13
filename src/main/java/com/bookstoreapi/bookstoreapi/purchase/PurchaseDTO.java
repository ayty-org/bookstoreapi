package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseDTO {

    @NotNull(message = "purchase client cannot be null")
    private Client client;

    @NotNull(message = "a purchase must have at least one book")
    private List<Book> purchasedBooks;

    private Double amount;
    private Date purchaseDate;

    @NotNull(message = "purchase status cannot be null")
    private Boolean isCompleted;

    public PurchaseDTO(Purchase purchase){
        this.client = purchase.getClient();
        this.purchasedBooks = purchase.getPurchasedBooks();
        this.amount = purchase.getAmount();
        this.purchaseDate = purchase.getPurchaseDate();
        this.isCompleted = purchase.getIsCompleted();
    }
}
