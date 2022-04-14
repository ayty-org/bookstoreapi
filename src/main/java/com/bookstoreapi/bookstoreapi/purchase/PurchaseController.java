package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.purchase.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/purchases")
@RestController
public class PurchaseController {

    private final GetAllPurchaseService getAllPurchaseService;
    private final GetPurchaseService getPurchaseService;
    private final PostPurchaseService postPurchaseService;
    private final PutPurchaseService putPurchaseService;
    private final DeletePurchaseService deletePurchaseService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDTO> list(){
        return PurchaseDTO.fromAll(getAllPurchaseService.findAll());
    }

    @GetMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDTO find(@PathVariable Long purchaseId){
        return PurchaseDTO.from(getPurchaseService.findById(purchaseId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseDTO save(@RequestBody @Valid PurchaseRecieveDTO purchase){
        return PurchaseDTO.from(postPurchaseService.save(PurchaseRecieveDTO.to(purchase),
                purchase.getClient(), purchase.getPurchasedBooks()));
    }

    @PutMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDTO update(@PathVariable Long purchaseId, @RequestBody @Valid PurchaseRecieveDTO purchase){
        return PurchaseDTO.from(putPurchaseService.update(purchaseId,
                PurchaseRecieveDTO.to(purchase), purchase.getClient(), purchase.getPurchasedBooks()));
    }

    @DeleteMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long purchaseId){
        deletePurchaseService.delete(purchaseId);
    }
}
