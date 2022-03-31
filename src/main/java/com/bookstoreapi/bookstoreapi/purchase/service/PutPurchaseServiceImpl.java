package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.service.GetBookService;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.service.GetClientService;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseResumedDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PutPurchaseServiceImpl implements PutPurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private GetClientService getClientService;
    @Autowired
    private GetBookService getBookService;


    @Override
    public PurchaseResumedDTO update(Long id, PurchaseDTO purchaseDTO){
        Purchase purchaseSaved = this.findById(id);
        verifyFields(purchaseDTO.getClient(), purchaseSaved.getPurchasedBooks());
        setAmountToPay(purchaseDTO);
        BeanUtils.copyProperties(purchaseDTO,purchaseSaved);
        purchaseRepository.save(purchaseSaved);
        return new PurchaseResumedDTO(purchaseDTO);
    }

    private Purchase findById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(() ->{
            throw new EntityNotFoundException("purchase with id "+id+" not found");
        });
    }

    private void verifyFields(Client client, List<Book> books){
        getClientService.findById(client.getId());
        for(Book book: books){
            getBookService.findById(book.getId());
        }
    }

    private void setAmountToPay(PurchaseDTO purchaseDTO){
        double amount = 0.0;
        for(Book book: purchaseDTO.getPurchasedBooks()){
            amount+= book.getPrice();
        }
        purchaseDTO.setAmount(amount);
    }
}
