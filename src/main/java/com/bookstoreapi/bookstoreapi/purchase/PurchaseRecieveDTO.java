package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.client.Client;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRecieveDTO {

    @NotNull(message = "purchase client cannot be null")
    private Long client;
    @NotNull(message = "a purchase must have at least one book")
    private List<Long> purchasedBooks;
    @NotNull(message = "purchase status cannot be null")
    private Boolean isCompleted;


    public static Purchase to(PurchaseRecieveDTO purchase) {
        Client client = Client.builder().id(purchase.getClient()).build();
        List<Book> books = new ArrayList<>();
        for(Long id: purchase.getPurchasedBooks()){
            books.add(Book.builder().id(id).build());
        }
        return Purchase.builder()
                .client(client)
                .purchasedBooks(books)
                .isCompleted(purchase.getIsCompleted())
                .build();
    }

}
