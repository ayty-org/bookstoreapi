package com.bookstoreapi.bookstoreapi.purchase;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/purchases")
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;


    @ApiOperation(value = "Return a list of all saved purchases")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Purchase> list(){
        return purchaseService.findAll();
    }

    @ApiOperation("Returns a unique purchase found by id")
    @GetMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public Purchase find(@PathVariable Long purchaseId){
        return purchaseService.findById(purchaseId);
    }

    @ApiOperation("Save a new purchase")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase save(@RequestBody Purchase purchase){
        return purchaseService.save(purchase);
    }

    @ApiOperation(value = "Updates all fields of a saved purchase by the purchase passed as a parameter")
    @PutMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public Purchase update(@PathVariable Long purchaseId, @RequestBody Purchase purchase){
        return purchaseService.update(purchaseId, purchase);
    }

    @ApiOperation(value = "Deletes a purchase by id")
    @DeleteMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long purchaseId){
        purchaseService.delete(purchaseId);
    }




}
