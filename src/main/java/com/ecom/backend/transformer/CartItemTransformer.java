package com.ecom.backend.transformer;

import com.ecom.backend.dto.CartItemDto.CartItemGetDto;
import com.ecom.backend.dto.CartItemDto.CartItemPostDto;
import com.ecom.backend.entity.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemTransformer extends AbstractTransformer<CartItem, CartItemGetDto> {
    @Override
    public CartItem toEntity(CartItemGetDto dto) {
        if(dto == null) {
            return null;
        }else{
            CartItem entity=new CartItem();
            entity.setId(dto.id());
            entity.setQuantite(dto.quantite());
            entity.setProduit(dto.produit());
            entity.setPanier(dto.panier());
            return entity;
        }
    }

    @Override
    public CartItemGetDto toDto(CartItem entity) {
        if(entity == null) {
            return null;
        }else{
            CartItemGetDto dto=new CartItemGetDto(
                    entity.getId(),
                    entity.getProduit(),
                    entity.getQuantite(),
                    entity.getPanier()
            );
            return dto;
        }
    }
    public CartItem toEntityPost(CartItemPostDto dto) {
        if(dto == null) {
            return null;
        }else{
            CartItem entity=new CartItem();
            entity.setId(dto.id());
            entity.setQuantite(dto.quantite());
            return entity;
        }
    }


    public CartItemPostDto toDtoPost(CartItem entity) {
        if(entity == null) {
            return null;
        }else{
            CartItemPostDto dto=new CartItemPostDto(
                    entity.getId(),
                    entity.getQuantite());
            return dto;
        }
    }
}
