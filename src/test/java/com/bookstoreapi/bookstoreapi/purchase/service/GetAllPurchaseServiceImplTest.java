package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetAllPurchaseServiceImplTest {

    @InjectMocks
    private GetAllPurchaseServiceImpl getAllPurchaseService;
    @Mock
    private PurchaseRepository repository;
    private final List<Purchase> allPurchases = new LinkedList<>();


    @BeforeEach
    void setUp(){
        Purchase purchase1 = new Purchase();
        purchase1.setId(1L);
        purchase1.setAmount(50);

        Purchase purchase2 = new Purchase();
        purchase2.setId(2L);
        purchase2.setAmount(40);


        Purchase purchase3 = new Purchase();
        purchase3.setId(3L);
        purchase3.setAmount(30);


        allPurchases.add(purchase1);
        allPurchases.add(purchase2);
        allPurchases.add(purchase3);
    }

    @Test
    void findAllTest(){
        when(repository.findAll()).thenReturn(allPurchases);
        List<PurchaseDTO> listReturned = getAllPurchaseService.findAll();
        assertThat(3, is(equalTo(listReturned.size())));
        for(int k = 0; k<3; k++){
            assertThat(allPurchases.get(k).getAmount(), is(equalTo(listReturned.get(k).getAmount())));
        }

    }

}