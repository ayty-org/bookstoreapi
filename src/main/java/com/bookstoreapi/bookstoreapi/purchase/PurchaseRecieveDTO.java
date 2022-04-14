package com.bookstoreapi.bookstoreapi.purchase;

import lombok.*;

import javax.validation.constraints.NotNull;
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
        return Purchase.builder()
                .isCompleted(purchase.getIsCompleted())
                .build();
    }

}
