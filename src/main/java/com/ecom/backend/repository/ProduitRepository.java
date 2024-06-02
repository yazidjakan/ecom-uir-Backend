package com.ecom.backend.repository;

import com.ecom.backend.entity.Produit;
import com.ecom.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{
    Optional<Produit> findByNom(String nom);

    List<Produit> findBySellerId(Long sellerId);
}
