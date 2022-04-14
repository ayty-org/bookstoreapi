package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseDTO;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UpdatePurchaseServiceImpl implements UpdatePurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;


    @Override
    public Purchase update(Long id, Purchase purchase){
        Optional<Purchase> purchaseSaved = purchaseRepository.findById(id);
        if(purchaseSaved.isPresent()){
            purchase.setPurchasedBooks(this.getBooks(purchase.getPurchasedBooks()));
            purchase.setClient(this.getClient(purchase.getClient().getId()));
            purchase.setAmount(this.getAmountToPay(purchase.getPurchasedBooks()));
            purchase.setId(id);
            updateBooksStock(purchase.getPurchasedBooks(), purchaseSaved.get().getPurchasedBooks());
            purchase.setPurchaseDate(new Date());
            return purchaseRepository.save(purchase);
        }
        throw new EntityNotFoundException(id, PurchaseDTO.getClassName());
    }

    private void updateBooksStock(List<Book> updated, List<Book> old){
          this.updateBooksStockToUp(old);
          this.updateBooksStockToDown(updated);
    }

    private List<Book> getBooks(List<Book> books){
        List<Book> bookList= new ArrayList<>();
        for(Book book: books){
            if(bookRepository.existsById(book.getId())){
                bookList.add(bookRepository.findById(book.getId()).get());
            }
            else{
                throw new EntityNotFoundException(book.getId(), BookDTO.getClassName());
            }
        }
        return bookList;
    }

    private Client getClient(Long id){
        if(clientRepository.existsById(id)){
            return clientRepository.findById(id).get();
        }
        throw new EntityNotFoundException(id, ClientDTO.getClassName());
    }


    private double getAmountToPay(List<Book> books){
        double amount = 0.0;
        for(Book book: books){
            amount += bookRepository.findById(book.getId()).get().getPrice();
        }
        return amount;
    }


    private void updateBooksStockToUp(List<Book> books){
        List<Book> booksToUpdate = new ArrayList<>();
        for(Book book: books){
            book.setQuantityInStock(book.getQuantityInStock()+1);
            booksToUpdate.add(book);
        }
        bookRepository.saveAll(booksToUpdate);
    }

    private void updateBooksStockToDown(List<Book> books){
        List<Book> booksToUpdate = new ArrayList<>();
        for(Book book: books){
            if(book.getQuantityInStock() > 0){
                book.setQuantityInStock(book.getQuantityInStock()-1);
                booksToUpdate.add(book);
            }else{
                throw new IllegalArgumentException("book with id "+book.getId()+" is out of stock");
            }
        }
        bookRepository.saveAll(booksToUpdate);
    }
}
