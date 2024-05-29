package com.ecom.backend.service.Impl;

import com.ecom.backend.dto.CartItemDto.CartItemGetDto;
import com.ecom.backend.dto.PanierDto.PanierGetDto;
import com.ecom.backend.dto.PanierDto.PanierPostDto;

import com.ecom.backend.entity.CartItem;
import com.ecom.backend.entity.Panier;

import com.ecom.backend.entity.Produit;
import com.ecom.backend.repository.CartItemRepository;
import com.ecom.backend.repository.PanierRepository;
import com.ecom.backend.repository.ProduitRepository;
import com.ecom.backend.service.facade.PanierService;
import com.ecom.backend.transformer.CartItemTransformer;
import com.ecom.backend.transformer.PanierTransformer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PanierServiceImpl implements PanierService {
    
    private final PanierRepository panierDao;
    private final PanierTransformer panierTransformer;
    private final CartItemRepository cartItemDao;
    private final CartItemTransformer cartItemTransformer;
    private final ProduitRepository produitDao;

    @Override
    public PanierGetDto findById(Long id) {
        log.info("Fetching Panier by ID: {}", id);
        return panierDao.findById(id)
                .map(panierTransformer::toDto)
                .orElseThrow(() -> {
                    log.error("Panier not found with ID: {}", id);
                    return new RuntimeException("Unable to find a Panier with the given Id : "+id);
                });
    }

    public List<CartItemGetDto> getPanier() {
        List<CartItem> cartItems=cartItemDao.findAll();
        return cartItemTransformer.toDto(cartItems);
    }
    public void addProduit(Long produitId) {
        Produit produit = produitDao.findById(produitId).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        CartItem cartItem = cartItemDao.findByProduit(produit).orElse(new CartItem(produit, 0));
        cartItem.setQuantite(cartItem.getQuantite() + 1);
        cartItemDao.save(cartItem);
    }

    public void removeProduit(Long produitId) {
        Produit produit = produitDao.findById(produitId).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        CartItem cartItem = cartItemDao.findByProduit(produit).orElseThrow(() -> new RuntimeException("Produit non trouvé dans le panier"));
        cartItemDao.delete(cartItem);
    }

    public void clearPanier() {
        cartItemDao.deleteAll();
    }

    @Override
    public List<PanierGetDto> findAll() {
        log.info("Fetching all Paniers");
        List<Panier> paniers=panierDao.findAll();
        if(paniers.isEmpty()){
            throw new RuntimeException("List of users is Empty");
        }
        return panierTransformer.toDto(paniers);
    }

    public PanierPostDto save(PanierPostDto dto) {

        log.info("Creating new Panier : {}", dto.id());

        try {
            Panier panier = panierTransformer.toEntityPost(dto);
            log.info("Successfully created Panier : {}", dto);
            return panierTransformer.toDtoPost(panierDao.save(panier));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating Panier : {}", dto, ex);
            throw new RuntimeException("An unexpected error occurred while creating the Panier."+ ex);
        }
    }

    @Override
    public PanierGetDto save(PanierGetDto dto) {

        try {
            Panier panier = panierTransformer.toEntity(dto);
            log.info("Successfully created Panier : {}", dto);
            return panierTransformer.toDto(panierDao.save(panier));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating Panier : {}", dto, ex);
            throw new RuntimeException("An unexpected error occurred while creating the Panier."+ ex);
        }
    }

    @Override
    public PanierGetDto update(PanierGetDto dto, Long id) {
        id = dto.id();
        PanierGetDto existingPanierDto = findById(id);
        Panier existingPanier = panierTransformer.toEntity(dto);
        existingPanier.setId(dto.id());
        existingPanier.setItems(dto.items());
        log.info("Successfully updated Panier with ID: {}", dto.id());
        return panierTransformer.toDto(panierDao.save(existingPanier));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting Panier with ID: {}", id);
        PanierGetDto foundPanier = findById(id);
        panierDao.deleteById(foundPanier.id());

        log.info("Successfully deleted Panier with ID: {}", id);
    }
}
