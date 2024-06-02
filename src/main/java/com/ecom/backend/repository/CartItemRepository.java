package com.ecom.backend.repository;

import com.ecom.backend.entity.CartItem;
import com.ecom.backend.entity.Produit;
import com.ecom.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByProduit(Produit produit);
}
