package com.ecom.backend;

import com.ecom.backend.dto.*;
import com.ecom.backend.dto.CartItemDto.CartItemPostDto;
import com.ecom.backend.dto.CategorieDto.CategoriePostDto;
import com.ecom.backend.dto.CommandeDto.CommandeGetDto;
import com.ecom.backend.dto.CommandeDto.CommandePostDto;
import com.ecom.backend.dto.PanierDto.PanierPostDto;
import com.ecom.backend.dto.ProduitDto.ProduitGetDto;
import com.ecom.backend.dto.ProduitDto.ProduitPostDto;
import com.ecom.backend.dto.StockDto.StockGetDto;
import com.ecom.backend.dto.StockDto.StockPostDto;
import com.ecom.backend.dto.VehiculeDto.VehiculePostDto;
import com.ecom.backend.entity.*;
import com.ecom.backend.enums.EtatProduit;
import com.ecom.backend.enums.StatutStock;
import com.ecom.backend.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
}