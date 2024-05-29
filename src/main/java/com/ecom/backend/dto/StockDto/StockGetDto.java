package com.ecom.backend.dto.StockDto;

import com.ecom.backend.entity.Categorie;
import com.ecom.backend.entity.Produit;
import com.ecom.backend.enums.StatutStock;

import java.util.List;

public record StockGetDto(
        Long id,
        String nomStock,
        StatutStock statutStock,
        List<Produit> produits
) {
}
