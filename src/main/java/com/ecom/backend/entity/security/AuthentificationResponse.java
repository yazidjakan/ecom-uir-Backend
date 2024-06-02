package com.ecom.backend.entity.security;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthentificationResponse {
    private String token;
    private List<String> roles;
    private Long userId;
}
