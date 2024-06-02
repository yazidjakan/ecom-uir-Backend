package com.ecom.backend.controller;

import com.ecom.backend.dto.RoleDto;
import com.ecom.backend.service.Impl.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RoleController {
    private final RoleServiceImpl roleService;
    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> findAll(){
        return ResponseEntity.ok(roleService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findById(id));
    }
    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDto> save(@RequestBody RoleDto dto){
        return new ResponseEntity<>(roleService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDto> update(@RequestBody RoleDto dto, @PathVariable Long id){
        return ResponseEntity.ok(roleService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
