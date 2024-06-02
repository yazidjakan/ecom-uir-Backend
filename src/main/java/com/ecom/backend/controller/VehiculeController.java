package com.ecom.backend.controller;

import com.ecom.backend.dto.VehiculeDto.VehiculeGetDto;
import com.ecom.backend.dto.VehiculeDto.VehiculePostDto;
import com.ecom.backend.service.Impl.VehiculeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicules")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class VehiculeController {
    private final VehiculeServiceImpl VehiculeService;

    @GetMapping("/")
    public ResponseEntity<List<VehiculeGetDto>> findAll() {
        return ResponseEntity.ok(VehiculeService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<VehiculeGetDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(VehiculeService.findById(id));
    }

    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VehiculePostDto> save(@RequestBody VehiculePostDto dto) {
        return new ResponseEntity<>(VehiculeService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VehiculeGetDto> update(@RequestBody VehiculeGetDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(VehiculeService.update(dto, id));
    }

    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        VehiculeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
