package com.ecom.backend.dto.CategorieDto;

import com.ecom.backend.entity.Produit;
import com.ecom.backend.entity.Stock;

import java.util.List;

public record CategorieGetDto(
        Long id,
        String nom,
        List<Produit> produits
) {
}
