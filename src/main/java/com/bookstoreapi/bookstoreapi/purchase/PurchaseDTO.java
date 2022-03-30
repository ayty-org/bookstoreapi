package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseDTO {

    private Client client;
    private List<Book> purchasedBooks;
    private double amount;
    private Date purchaseDate;
    private boolean isCompleted;

    public PurchaseDTO(Purchase purchase){
        this.client = purchase.getClient();
        this.purchasedBooks = purchase.getPurchasedBooks();
        this.amount = purchase.getAmount();
        this.purchaseDate = purchase.getPurchaseDate();
        this.isCompleted = purchase.isCompleted();
    }
}
