package com.ecom.backend.entity;

import com.ecom.backend.enums.EtatProduit;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private Double prix;
    private Integer quantite;
    private String image;

    @Enumerated(EnumType.STRING)
    private EtatProduit etatProduit;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    @JsonManagedReference
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @JsonManagedReference
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    @JsonManagedReference
    private Vehicule vehicule;
}
