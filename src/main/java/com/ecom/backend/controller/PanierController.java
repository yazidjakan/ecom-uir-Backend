package com.ecom.backend.controller;

import com.ecom.backend.dto.CartItemDto.CartItemGetDto;
import com.ecom.backend.dto.PanierDto.PanierGetDto;
import com.ecom.backend.dto.PanierDto.PanierPostDto;
import com.ecom.backend.entity.CartItem;
import com.ecom.backend.entity.Panier;
import com.ecom.backend.entity.Produit;
import com.ecom.backend.service.Impl.PanierServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/panier")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PanierController {
    private final PanierServiceImpl panierService;


    @GetMapping("/")
    public List<CartItemGetDto> getPanier() {
        return panierService.getPanier();
    }

    @PostMapping("/add/{produitId}")
    public void addProduit(@PathVariable Long produitId) {
        panierService.addProduit(produitId);
    }

    @PostMapping("/remove/{produitId}")
    public void removeProduit(@PathVariable Long produitId) {
        panierService.removeProduit(produitId);
    }

    @PostMapping("/clear")
    public void clearPanier() {
        panierService.clearPanier();
    }
}

