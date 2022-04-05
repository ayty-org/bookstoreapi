package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DeletePurchaseServiceImplTest {

    @InjectMocks
    private DeletePurchaseServiceImpl deletePurchaseService;
    @Mock
    private PurchaseRepository repository;
    @Mock
    private PurchaseService service;
    private final Map<Long, Purchase> purchases = new HashMap<>();


    @BeforeEach
    void setUp() {
        Purchase purchase1 = new Purchase();
        purchase1.setId(1L);

        Purchase purchase2 = new Purchase();
        purchase2.setId(2L);

        purchases.put(1L, purchase1);
        purchases.put(2L, purchase2);
    }

    @Test
    void deleteWhenIdExistTest(){
        when(service.findById(1L)).thenReturn(purchases.get(1L));
        deletePurchaseService.delete(1L);
    }

    @Test
    void deleteWhenIdDontExist(){
        when(service.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, ()-> deletePurchaseService.delete(1L));
    }
}