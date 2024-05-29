package com.ecom.backend.dto.CommandeDto;

import com.ecom.backend.entity.User;
import com.ecom.backend.enums.EtatCommande;

public record CommandePostDto(
        Long id,
        EtatCommande etatCommande
) {
}
