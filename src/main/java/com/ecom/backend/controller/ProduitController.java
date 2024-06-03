package com.ecom.backend.controller;

import com.ecom.backend.dto.ProduitDto.ProduitGetDto;
import com.ecom.backend.dto.ProduitDto.ProduitPostDto;
import com.ecom.backend.dto.UserDto;
import com.ecom.backend.entity.Produit;
import com.ecom.backend.entity.User;
import com.ecom.backend.repository.UserRepository;
import com.ecom.backend.service.Impl.ProduitServiceImpl;
import com.ecom.backend.service.Impl.UserServiceImpl;
import com.ecom.backend.transformer.UserTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Slf4j
public class ProduitController {
    private final ProduitServiceImpl produitService;
    private final UserServiceImpl userService;
    private final UserTransformer userTransformer;


    @GetMapping("/")
    public ResponseEntity<List<ProduitGetDto>> findAll(){
        return ResponseEntity.ok(produitService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ProduitGetDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(produitService.findById(id));
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Produit>> getSellerProducts(@PathVariable Long sellerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long idSeller = userService.findByUsername(userDetails.getUsername()).id();
        List<Produit> products = produitService.getProductsBySeller(sellerId);
        return ResponseEntity.ok(products);
    }
    @PostMapping("/")
    public ResponseEntity<ProduitPostDto> save(@RequestBody ProduitPostDto dto){
        log.info("Adding new Product");
        return new ResponseEntity<>(produitService.save(dto), HttpStatus.CREATED);
    }

    @PostMapping("/seller/{sellerId}")
    public ResponseEntity<ProduitPostDto> saveProductBySeller(@RequestBody ProduitPostDto dto, @PathVariable Long sellerId){
        try {
            UserDto sellerDto = userService.findById(sellerId);
            if (sellerDto == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            ProduitPostDto createdProduct = produitService.saveProductBySeller(dto, sellerDto);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ProduitGetDto> update(@RequestBody ProduitGetDto dto, @PathVariable Long id){
        log.info("Received request to update product with ID: {}", id);
        return ResponseEntity.ok(produitService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        produitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
