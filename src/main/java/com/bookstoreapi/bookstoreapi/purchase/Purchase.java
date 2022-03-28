package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.client.Client;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "purchases")
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
    private String status;


}
