package com.ecom.backend.repository;

import com.ecom.backend.entity.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findByProduitId(Long produitId);
    List<Avis> findByUserId(Long userId);
}

