package com.bookstoreapi.bookstoreapi.purchase;


import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseResumedDTO {


    private ClientDTO client;
    private List<BookDTO> books;
    private double amount;
    private Date date;
    private boolean isCompleted;

    public PurchaseResumedDTO(PurchaseDTO purchaseDTO){
        this.client = new ClientDTO(purchaseDTO.getClient());
        this.books = purchaseDTO.getPurchasedBooks().stream().map(BookDTO::new).collect(Collectors.toList());
        this.amount = purchaseDTO.getAmount();
        this.date = purchaseDTO.getPurchaseDate();
        this.isCompleted = purchaseDTO.isCompleted();
    }

}
