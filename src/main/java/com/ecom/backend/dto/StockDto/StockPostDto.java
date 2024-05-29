package com.ecom.backend.dto.StockDto;

import com.ecom.backend.enums.StatutStock;

public record StockPostDto(
        Long id,
        String nomStock,
        StatutStock statutStock
) {
}
