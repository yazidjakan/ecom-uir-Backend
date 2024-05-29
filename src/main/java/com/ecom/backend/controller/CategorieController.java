package com.ecom.backend.controller;

import com.ecom.backend.dto.CategorieDto.CategorieGetDto;
import com.ecom.backend.dto.CategorieDto.CategoriePostDto;
import com.ecom.backend.service.Impl.CategorieServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CategorieController {
    private final CategorieServiceImpl categorieService;
    @GetMapping("/")
    public ResponseEntity<List<CategorieGetDto>> findAll(){
        return ResponseEntity.ok(categorieService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<CategorieGetDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(categorieService.findById(id));
    }
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriePostDto> save(@RequestBody CategoriePostDto dto){
        return new ResponseEntity<>(categorieService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategorieGetDto> update(@RequestBody CategorieGetDto dto, @PathVariable Long id){
        return ResponseEntity.ok(categorieService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        categorieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
