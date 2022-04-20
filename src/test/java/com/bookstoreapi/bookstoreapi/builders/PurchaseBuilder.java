package com.bookstoreapi.bookstoreapi.builders;

import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRecieveDTO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PurchaseBuilder {

    public static Purchase purchase1L(){
        return Purchase.builder()
                .id(1L)
                .client(ClientBuilder.clientJenipapo1())
                .purchasedBooks(BookBuilder.bookList())
                .amount(100.0)
                .purchaseDate(new Date(14112020))
                .isCompleted(true)
                .build();
    }

    public static Purchase purchase2L(){
        return Purchase.builder()
                .id(2L)
                .client(ClientBuilder.clientAna2())
                .purchasedBooks(BookBuilder.bookList())
                .amount(200.0)
                .purchaseDate(new Date(10102010))
                .isCompleted(false)
                .build();
    }

    public static PurchaseRecieveDTO purchaseRecieve(){
        return PurchaseRecieveDTO.builder()
                .client(1L)
                .purchasedBooks(List.of(1L,2L,3L))
                .isCompleted(true)
                .build();
    }

    public static List<Purchase> purchaseList(){
        List<Purchase> purchases = new LinkedList<>();
        purchases.add(purchase1L());
        purchases.add(purchase2L());
        return purchases;
    }
}
