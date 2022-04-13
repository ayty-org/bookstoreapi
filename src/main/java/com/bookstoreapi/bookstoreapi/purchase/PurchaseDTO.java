package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.categories.CategoryDTO;
import com.bookstoreapi.bookstoreapi.client.Client;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
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

    public static PurchaseDTO from(Purchase purchase) {
        return PurchaseDTO.builder()
                .client(purchase.getClient())
                .purchasedBooks(purchase.getPurchasedBooks())
                .amount(purchase.getAmount())
                .purchaseDate(purchase.getPurchaseDate())
                .isCompleted(purchase.getIsCompleted())
                .build();
    }

    public static Purchase from(PurchaseDTO purchase) {
        return Purchase.builder()
                .client(purchase.getClient())
                .purchasedBooks(purchase.getPurchasedBooks())
                .amount(purchase.getAmount())
                .purchaseDate(purchase.getPurchaseDate())
                .isCompleted(purchase.getIsCompleted())
                .build();
    }

    public static List<PurchaseDTO> fromAll(List<Purchase> purchases) {
        return purchases.stream()
                .map(PurchaseDTO::from)
                .collect(Collectors.toList());
    }

}
