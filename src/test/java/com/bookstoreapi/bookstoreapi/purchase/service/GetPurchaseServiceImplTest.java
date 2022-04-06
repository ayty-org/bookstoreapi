package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetPurchaseServiceImplTest {

    @InjectMocks
    private GetPurchaseServiceImpl getPurchaseService;
    @Mock
    private PurchaseService service;
    private final Map<Long, Purchase> purchases = new HashMap<>();


    @BeforeEach
    void setUp(){
        Purchase purchase1 = new Purchase();
        purchase1.setId(1L);
        purchase1.setAmount(48.99);

        Purchase purchase2 = new Purchase();
        purchase2.setId(2L);
        purchase2.setAmount(99.99);

        purchases.put(1L, purchase1);
        purchases.put(2L, purchase2);
    }

    @Test
    void testGetByIdWhenIdExist(){
        when(service.findById(1L)).thenReturn(purchases.get(1L));
        when(service.findById(2L)).thenReturn(purchases.get(2L));

        PurchaseDTO purchaseDTO = getPurchaseService.findById(1L);
        assertThat(48.99, is(equalTo(purchaseDTO.getAmount())));
        purchaseDTO = getPurchaseService.findById(2L);
        assertThat(99.99, is(equalTo(purchaseDTO.getAmount())));
    }

    @Test
    void testGetByIdWhenIdDontExist(){
        when(service.findById(anyLong())).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, ()-> getPurchaseService.findById(3L));
    }
}