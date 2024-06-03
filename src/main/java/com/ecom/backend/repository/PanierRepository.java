package com.ecom.backend.repository;

import com.ecom.backend.entity.Panier;
import com.ecom.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {
    Optional<Panier> findByUser(User user);
}
