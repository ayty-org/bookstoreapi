package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PutPurchaseServiceImpl implements PutPurchaseService{

    private final PurchaseRepository purchaseRepository;
    private final PurchaseService purchaseService;


    @Override
    public PurchaseDTO update(Long id, PurchaseDTO purchaseDTO){
        Purchase purchaseSaved = purchaseService.findById(id);

        purchaseDTO.setPurchasedBooks(purchaseService.getBooks(purchaseDTO.getPurchasedBooks()));
        //purchaseDTO.setClient(purchaseService.getClient(purchaseDTO.getClient()));
        purchaseDTO.setAmount(purchaseService.getAmountToPay(purchaseDTO.getPurchasedBooks()));

        checkUpdatesBooksStock(purchaseDTO.getPurchasedBooks(), purchaseSaved.getPurchasedBooks());
        BeanUtils.copyProperties(purchaseDTO,purchaseSaved);
        purchaseRepository.save(purchaseSaved);
        return purchaseDTO;
    }

    private void checkUpdatesBooksStock(List<Book> updated, List<Book> old){
      for(Book book: updated){
          if(!old.contains(book)){
              List<Book> bookToUpdate = new ArrayList<>();
              purchaseService.updateBooksStock(bookToUpdate);
          }
      }
    }
}
