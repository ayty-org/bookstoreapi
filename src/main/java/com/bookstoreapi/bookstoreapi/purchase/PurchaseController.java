package com.bookstoreapi.bookstoreapi.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/purchases")
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Purchase> list(){
        return purchaseService.findAll();
    }

    @GetMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public Purchase find(@PathVariable Long purchaseId){
        return purchaseService.findById(purchaseId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase save(@RequestBody Purchase purchase){
        return purchaseService.save(purchase);
    }

    @PutMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public Purchase update(@PathVariable Long purchaseId, @RequestBody Purchase purchase){
        return purchaseService.update(purchaseId, purchase);
    }

    @DeleteMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long purchaseId){
        purchaseService.delete(purchaseId);
    }
}
