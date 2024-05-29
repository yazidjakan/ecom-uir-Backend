package com.ecom.backend.dto.CartItemDto;

import com.ecom.backend.entity.Panier;
import com.ecom.backend.entity.Produit;

public record CartItemGetDto(
        Long id,
        Produit produit,
        Integer quantite,
        Panier panier
) {
}
