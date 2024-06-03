package com.ecom.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    @JsonManagedReference
    private Produit produit;

    private Integer quantite;

    @ManyToOne
    @JoinColumn(name = "panier_id")
    @JsonBackReference
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CartItem(Produit produit, Panier panier, int quantite) {
        this.produit = produit;
        this.panier = panier;
        this.quantite = quantite;
    }

    public CartItem(Produit produit, int quantite) {
        this.produit=produit;
        this.quantite=quantite;
    }

    public CartItem(Produit produit, Panier panier, User user, int quantite) {
        this.produit = produit;
        this.panier = panier;
        this.quantite = quantite;
        this.user=user;
    }
}
