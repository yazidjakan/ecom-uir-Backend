package com.ecom.backend.repository;

import com.ecom.backend.entity.Stock;
import com.ecom.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByNomStock(String nomStock);
}
