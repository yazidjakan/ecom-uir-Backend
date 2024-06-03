package com.ecom.backend.controller;

import com.ecom.backend.dto.CommandeDto.CommandeGetDto;
import com.ecom.backend.dto.CommandeDto.CommandePostDto;
import com.ecom.backend.service.Impl.CommandeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commandes")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CommandeController {
    private final CommandeServiceImpl commandeService;
    @GetMapping("/")
    public ResponseEntity<List<CommandeGetDto>> findAll(){
        return ResponseEntity.ok(commandeService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<CommandeGetDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(commandeService.findById(id));
    }
    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommandePostDto> save(@RequestBody CommandePostDto dto){
        return new ResponseEntity<>(commandeService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<CommandeGetDto> update(@RequestBody CommandeGetDto dto, @PathVariable Long id){
        return ResponseEntity.ok(commandeService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        commandeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
