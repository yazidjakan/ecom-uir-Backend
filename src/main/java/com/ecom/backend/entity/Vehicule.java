package com.ecom.backend.entity;

import com.ecom.backend.enums.TypeVehicule;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomVehicule;

    private String image;

    @Enumerated(EnumType.STRING)
    private TypeVehicule typeVehicule;

    @Nullable
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "produit-vehicule")
    private List<Produit> produits;
}
