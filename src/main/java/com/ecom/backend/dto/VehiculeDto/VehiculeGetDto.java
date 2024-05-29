package com.ecom.backend.dto.VehiculeDto;

import com.ecom.backend.entity.Produit;
import com.ecom.backend.enums.TypeVehicule;

import java.util.List;

public record VehiculeGetDto(
        Long id,
        String nomVehicule,
        String image,
        TypeVehicule typeVehicule,
        List<Produit> produits
) {
}
