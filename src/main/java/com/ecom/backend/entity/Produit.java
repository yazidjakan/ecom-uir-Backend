package com.ecom.backend.entity;

import com.ecom.backend.enums.EtatProduit;
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
    @JsonBackReference(value = "produit-categorie")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @JsonBackReference(value = "produit-stock")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    @JsonBackReference(value = "produit-vehicule")
    private Vehicule vehicule;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avis> avis = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
    public void addAvis(Avis avis) {
        this.avis.add(avis);
        avis.setProduit(this);
    }

    public void removeAvis(Avis avis) {
        this.avis.remove(avis);
        avis.setProduit(null);
    }
}
