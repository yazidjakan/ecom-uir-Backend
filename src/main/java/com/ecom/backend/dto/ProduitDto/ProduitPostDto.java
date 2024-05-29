package com.ecom.backend.dto.ProduitDto;

import com.ecom.backend.enums.EtatProduit;

public record ProduitPostDto(
        Long id,
        String nom,
        String description,
        double prix,
        Integer quantite,
        String image,
        EtatProduit etatProduit
) {
}
