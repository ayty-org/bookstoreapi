package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.purchase.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/purchases")
@RestController
public class PurchaseController {

    @Autowired
    private GetAllPurchaseService getAllPurchaseService;
    @Autowired
    private GetPurchaseService getPurchaseService;
    @Autowired
    private PostPurchaseService postPurchaseService;
    @Autowired
    private PutPurchaseService putPurchaseService;
    @Autowired
    private DeletePurchaseService deletePurchaseService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseResumedDTO> list(){
        return getAllPurchaseService.findAll();
    }

    @GetMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseResumedDTO find(@PathVariable Long purchaseId){
        return getPurchaseService.findById(purchaseId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseDTO save(@RequestBody @Valid PurchaseDTO purchase){
        return postPurchaseService.save(purchase);
    }

    @PutMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseResumedDTO update(@PathVariable Long purchaseId, @RequestBody @Valid PurchaseDTO purchase){
        return putPurchaseService.update(purchaseId, purchase);
    }

    @DeleteMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long purchaseId){
        deletePurchaseService.delete(purchaseId);
    }
}
