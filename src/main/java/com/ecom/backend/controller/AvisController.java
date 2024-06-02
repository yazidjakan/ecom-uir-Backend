package com.ecom.backend.controller;

import com.ecom.backend.entity.Avis;

import com.ecom.backend.service.Impl.AvisServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/avis")
@RequiredArgsConstructor
public class AvisController {
    private final AvisServiceImpl avisService;

    @GetMapping("/produit/{produitId}")
    public ResponseEntity<List<Avis>> getAvisByProduitId(@PathVariable Long produitId) {
        return ResponseEntity.ok(avisService.findByProduitId(produitId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Avis> createAvis(@RequestBody Avis avis) {
        return ResponseEntity.ok(avisService.save(avis));
    }
}
