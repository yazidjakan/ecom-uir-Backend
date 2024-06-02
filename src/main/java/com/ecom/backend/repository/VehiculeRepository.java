package com.ecom.backend.repository;

import com.ecom.backend.entity.Categorie;
import com.ecom.backend.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    Optional<Vehicule> findByNomVehicule(String nomVehicule);
}
