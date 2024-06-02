package com.ecom.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,  mappedBy = "panier")
    @JsonManagedReference
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Produit produit) {
        for (CartItem item : items) {
            if (item.getProduit().getId().equals(produit.getId())) {
                item.setQuantite(item.getQuantite() + 1);
                return;
            }
        }
        items.add(new CartItem(produit, produit.getQuantite()));
    }

    public void removeItem(Produit produit) {
        items.removeIf(item -> item.getProduit().getId().equals(produit.getId()));
    }

    public void clear() {
        items.clear();
    }
}