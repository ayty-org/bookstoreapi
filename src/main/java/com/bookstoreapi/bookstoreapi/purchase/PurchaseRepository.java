package com.bookstoreapi.bookstoreapi.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    boolean existsByPurchasedBooksId(Long id);
    boolean existsByClientId(Long id);
}
