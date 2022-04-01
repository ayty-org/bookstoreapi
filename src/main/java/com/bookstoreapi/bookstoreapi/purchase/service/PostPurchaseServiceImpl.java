package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.service.GetBookService;
import com.bookstoreapi.bookstoreapi.book.service.PutBookService;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.service.GetClientService;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseResumedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostPurchaseServiceImpl implements PostPurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private GetClientService getClientService;
    @Autowired
    private GetBookService getBookService;
    @Autowired
    private PutBookService putBookService;


    @Override
    public PurchaseResumedDTO save(PurchaseDTO purchaseDTO){
        verifyFields(purchaseDTO.getClient(), purchaseDTO.getPurchasedBooks());
        verifyIfBookHasQuantity(purchaseDTO);
        setAmountToPay(purchaseDTO);
        purchaseRepository.save(new Purchase(purchaseDTO));
        return new PurchaseResumedDTO(purchaseDTO);
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
            amount += getBookService.findById(book.getId()).getPrice();
        }
        purchaseDTO.setAmount(amount);
    }

    private void verifyIfBookHasQuantity(PurchaseDTO purchaseDTO){
        for(Book book: purchaseDTO.getPurchasedBooks()){
            BookDTO bookSaved = getBookService.findById(book.getId());
            if(bookSaved.getQuantityInStock() > 0){
                bookSaved.setQuantityInStock(bookSaved.getQuantityInStock()-1);
                putBookService.update(book.getId(), bookSaved);
            }else{
                throw new IllegalArgumentException("book with id "+book.getId()+" don't has stock");
            }
        }
    }
}
