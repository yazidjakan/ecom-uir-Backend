package com.ecom.backend.controller;

import com.ecom.backend.dto.StockDto.StockGetDto;
import com.ecom.backend.dto.StockDto.StockPostDto;
import com.ecom.backend.service.Impl.StockServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class StockController {
    private final StockServiceImpl stockService;

    @GetMapping("/")
    public ResponseEntity<List<StockGetDto>> findAll(){
        return ResponseEntity.ok(stockService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<StockGetDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(stockService.findById(id));
    }
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StockPostDto> save(@RequestBody StockPostDto dto){
        return new ResponseEntity<>(stockService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StockGetDto> update(@RequestBody StockGetDto dto, @PathVariable Long id){
        return ResponseEntity.ok(stockService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        stockService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
