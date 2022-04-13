package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "purchases")
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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> purchasedBooks;

    private Double amount;
    private Date purchaseDate;
    private Boolean isCompleted;

    public Purchase(PurchaseDTO purchaseDTO){
        this.client = purchaseDTO.getClient();
        this.purchasedBooks = purchaseDTO.getPurchasedBooks();
        this.amount = purchaseDTO.getAmount();
        this.purchaseDate = purchaseDTO.getPurchaseDate();
        this.isCompleted = purchaseDTO.getIsCompleted();
    }
}
