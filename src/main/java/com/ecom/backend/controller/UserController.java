package com.ecom.backend.controller;

import com.ecom.backend.dto.UserDto;
import com.ecom.backend.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }
    @PostMapping("/")
    public ResponseEntity<UserDto> save(@RequestBody UserDto dto){
        return new ResponseEntity<>(userService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> update(@RequestBody UserDto dto, @PathVariable Long id){
        return ResponseEntity.ok(userService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
