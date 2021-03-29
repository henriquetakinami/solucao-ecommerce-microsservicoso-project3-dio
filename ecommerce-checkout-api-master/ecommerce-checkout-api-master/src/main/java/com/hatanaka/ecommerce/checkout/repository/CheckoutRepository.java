package com.hatanaka.ecommerce.checkout.repository;

import com.hatanaka.ecommerce.checkout.entity.CheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// o que realmente acessa as entidades
@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Long> { // Qual entidade acessa, e o tipo do Id
}
