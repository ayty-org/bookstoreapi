package com.bookstoreapi.bookstoreapi.purchase;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;


    public List<PurchaseDTO> findAll(){
        return purchaseRepository.findAll()
                .stream()
                .map(PurchaseDTO::new).collect(Collectors.toList());
    }

    private Purchase findById(Long id){
        return purchaseRepository.findById(id).orElseThrow(() ->{
            throw new EntityNotFoundException("Purchase with id "+id+" not found");
        });
    }

    public PurchaseDTO getDTO(Long id){
        return new PurchaseDTO(this.findById(id));
    }

    public PurchaseDTO save(PurchaseDTO purchaseDTO){
        purchaseRepository.save(new Purchase(purchaseDTO));
        return purchaseDTO;
    }

    public void delete(Long id){
        purchaseRepository.delete(this.findById(id));
    }

    public PurchaseDTO update(Long id,PurchaseDTO purchaseDTO){
        Purchase purchaseSaved = this.findById(id);
        BeanUtils.copyProperties(purchaseDTO,purchaseSaved);
        purchaseRepository.save(purchaseSaved);
        return purchaseDTO;
    }
}
