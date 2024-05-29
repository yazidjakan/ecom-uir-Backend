package com.ecom.backend.service.Impl;

import com.ecom.backend.dto.CartItemDto.CartItemGetDto;
import com.ecom.backend.dto.CartItemDto.CartItemPostDto;
import com.ecom.backend.entity.CartItem;
import com.ecom.backend.repository.CartItemRepository;
import com.ecom.backend.service.facade.CartItemService;
import com.ecom.backend.transformer.CartItemTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService {
    private final CartItemTransformer cartItemTransformer;
    private final CartItemRepository cartItemDao;

    @Override
    public CartItemGetDto findById(Long id) {
        log.info("Fetching CartItem by ID: {}", id);
        return cartItemDao.findById(id)
                .map(cartItemTransformer::toDto)
                .orElseThrow(() -> {
                    log.error("CartItem not found with ID: {}", id);
                    return new RuntimeException("Unable to find a CartItem with the given Id : "+id);
                });
    }

    @Override
    public List<CartItemGetDto> findAll() {
        log.info("Fetching all CartItems");
        List<CartItem> CartItems=cartItemDao.findAll();
        if(CartItems.isEmpty()){
            throw new RuntimeException("List of users is Empty");
        }
        return cartItemTransformer.toDto(CartItems);
    }

    public CartItemPostDto save(CartItemPostDto dto) {

        log.info("Creating new CartItem : {}", dto.id());

        try {
            CartItem cartItem = cartItemTransformer.toEntityPost(dto);
            log.info("Successfully created CartItem : {}", dto);
            return cartItemTransformer.toDtoPost(cartItemDao.save(cartItem));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating CartItem : {}", dto, ex);
            throw new RuntimeException("An unexpected error occurred while creating the CartItem."+ ex);
        }
    }

    @Override
    public CartItemGetDto save(CartItemGetDto dto) {

        try {
            CartItem cartItem = cartItemTransformer.toEntity(dto);
            log.info("Successfully created CartItem : {}", dto);
            return cartItemTransformer.toDto(cartItemDao.save(cartItem));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating CartItem : {}", dto, ex);
            throw new RuntimeException("An unexpected error occurred while creating the CartItem."+ ex);
        }
    }

    @Override
    public CartItemGetDto update(CartItemGetDto dto, Long id) {
        id = dto.id();
        CartItemGetDto existingCartItemDto = findById(id);
        CartItem existingCartItem = cartItemTransformer.toEntity(dto);
        existingCartItem.setId(dto.id());
        existingCartItem.setQuantite(dto.quantite());
        existingCartItem.setProduit(dto.produit());
        existingCartItem.setPanier(dto.panier());
        log.info("Successfully updated CartItem with ID: {}", dto.id());
        return cartItemTransformer.toDto(cartItemDao.save(existingCartItem));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting CartItem with ID: {}", id);
        CartItemGetDto foundCartItem = findById(id);
        cartItemDao.deleteById(foundCartItem.id());

        log.info("Successfully deleted CartItem with ID: {}", id);
    }
}
