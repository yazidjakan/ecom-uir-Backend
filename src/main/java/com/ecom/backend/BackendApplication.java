package com.ecom.backend;

import com.ecom.backend.repository.CartItemRepository;
import com.ecom.backend.repository.CategorieRepository;
import com.ecom.backend.repository.CommandeRepository;
import com.ecom.backend.repository.PanierRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;
import java.util.Base64;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
