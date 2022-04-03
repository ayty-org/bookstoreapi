package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.client.Client;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchProfile;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Purchase {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToMany
    private List<Book> purchasedBooks;

    private double amount;
    private Date purchaseDate;
    private boolean isCompleted;

    public Purchase(PurchaseDTO purchaseDTO){
        this.client = purchaseDTO.getClient();
        this.purchasedBooks = purchaseDTO.getPurchasedBooks();
        this.amount = purchaseDTO.getAmount();
        this.purchaseDate = purchaseDTO.getPurchaseDate();
        this.isCompleted = purchaseDTO.isCompleted();
    }
}
