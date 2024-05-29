package com.ecom.backend.dto.PanierDto;

import com.ecom.backend.entity.CartItem;

import java.util.List;

public record PanierGetDto(
        Long id,
        List<CartItem> items
) {
}
