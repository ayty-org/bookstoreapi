package com.bookstoreapi.bookstoreapi.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/purchases")
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDTO> list(){
        return purchaseService.findAll();
    }

    @GetMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDTO find(@PathVariable Long purchaseId){
        return purchaseService.getDTO(purchaseId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseDTO save(@RequestBody @Valid PurchaseDTO purchase){
        return purchaseService.save(purchase);
    }

    @PutMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDTO update(@PathVariable Long purchaseId, @RequestBody @Valid PurchaseDTO purchase){
        return purchaseService.update(purchaseId, purchase);
    }

    @DeleteMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long purchaseId){
        purchaseService.delete(purchaseId);
    }
}
