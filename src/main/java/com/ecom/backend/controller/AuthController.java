package com.ecom.backend.controller;

import com.ecom.backend.entity.security.AuthentificationRequest;
import com.ecom.backend.entity.security.AuthentificationResponse;
import com.ecom.backend.service.Impl.Security.AuthentificationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    private final AuthentificationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<AuthentificationResponse> authenticate(@RequestBody AuthentificationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
