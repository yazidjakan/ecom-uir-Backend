package com.ecom.backend.controller;

import com.ecom.backend.dto.ProduitDto.ProduitGetDto;
import com.ecom.backend.dto.ProduitDto.ProduitPostDto;
import com.ecom.backend.service.Impl.ProduitServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitServiceImpl produitService;

    @GetMapping("/")
    public ResponseEntity<List<ProduitGetDto>> findAll(){
        return ResponseEntity.ok(produitService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ProduitGetDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(produitService.findById(id));
    }
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProduitPostDto> save(@RequestBody ProduitPostDto dto){
        return new ResponseEntity<>(produitService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProduitGetDto> update(@RequestBody ProduitGetDto dto, @PathVariable Long id){
        return ResponseEntity.ok(produitService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        produitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}