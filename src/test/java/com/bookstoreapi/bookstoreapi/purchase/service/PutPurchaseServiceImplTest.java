package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PutPurchaseServiceImplTest {

    @InjectMocks
    private PutPurchaseServiceImpl putPurchaseService;
    @Mock
    private PurchaseRepository repository;
    @Mock
    private PurchaseService service;

    @Test
    void updateTest(){
        Purchase purchaseOld = new Purchase();
        purchaseOld.setId(1L);

        Client clientOld = new Client();
        clientOld.setName("old");
        purchaseOld.setClient(clientOld);

        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setPurchasedBooks(new ArrayList<>());
        Client clientUpdate = new Client();
        clientUpdate.setName("updated");
        purchaseDTO.setClient(clientUpdate);


        when(service.findById(1L)).thenReturn(purchaseOld);
        when(repository.save(any())).thenReturn(purchaseOld);
        when(service.getClient(any())).thenReturn(clientUpdate);
        when(service.getBooks(any())).thenReturn(new ArrayList<>());


        assertThat("updated", is(equalTo
                (putPurchaseService.update(1L, purchaseDTO).getClient().getName())));
        assertInstanceOf(PurchaseDTO.class, putPurchaseService.update(1L, purchaseDTO));
    }

}