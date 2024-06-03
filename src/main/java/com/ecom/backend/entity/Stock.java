package com.ecom.backend.entity;

import com.ecom.backend.enums.StatutStock;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomStock;

    @Enumerated(EnumType.STRING)
    private StatutStock statutStock;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produit> produits = new ArrayList<>();


    public void addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setStock(this);
    }

    public void removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setStock(null);
    }
}
