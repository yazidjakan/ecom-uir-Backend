package com.ecom.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    @JsonBackReference(value = "produit-avis")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String commentaire;
    private int note;
}
