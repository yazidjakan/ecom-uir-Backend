package com.ecom.backend.dto.ProduitDto;

import com.ecom.backend.entity.Categorie;
import com.ecom.backend.entity.Stock;
import com.ecom.backend.entity.Vehicule;
import com.ecom.backend.enums.EtatProduit;

public record ProduitGetDto(
        Long id,
        String nom,
        String description,
        double prix,
        Integer quantite,
        String image,
        EtatProduit etatProduit,
        Categorie categorie,
        Stock stock,
        Vehicule vehicule
) {
}
