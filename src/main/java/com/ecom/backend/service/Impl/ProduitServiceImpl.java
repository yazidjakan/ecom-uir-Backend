package com.ecom.backend.service.Impl;

import com.ecom.backend.dto.ProduitDto.ProduitGetDto;
import com.ecom.backend.dto.ProduitDto.ProduitPostDto;
import com.ecom.backend.dto.UserDto;
import com.ecom.backend.entity.Produit;
import com.ecom.backend.entity.User;
import com.ecom.backend.repository.ProduitRepository;
import com.ecom.backend.repository.UserRepository;
import com.ecom.backend.service.facade.ProduitService;
import com.ecom.backend.transformer.ProduitTransformer;
import com.ecom.backend.transformer.UserTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitDao;
    private final ProduitTransformer produitTransformer;
    private final UserTransformer userTransformer;
    private final UserRepository userDao;
    @Override
    public ProduitGetDto findById(Long id) {
        log.info("Fetching produit by ID: {}", id);
        return produitDao.findById(id)
                .map(produitTransformer::toDto)
                .orElseThrow(() ->{
                    log.error("Produit not found with ID: {}", id);
                    return new RuntimeException("Unable to find a produit with the given Id : "+id);
                });
    }

    @Override
    public List<ProduitGetDto> findAll() {
        List<Produit> products=produitDao.findAll();
        if(products.isEmpty()){
            throw new RuntimeException("List of produits is Empty");
        }
        return produitTransformer.toDto(products);
    }

    public List<Produit> getProductsBySeller(Long sellerId) {
        return produitDao.findBySellerId(sellerId);
    }
    @Override
    public ProduitGetDto save(ProduitGetDto dto) {
        log.info("Creating new product with name: {}", dto.nom());

        if(produitDao.findByNom(dto.nom()).isPresent())
        {
            log.warn("Attempted to create a duplicate product with name: {}", dto.nom());
            throw new RuntimeException("This product name already exists");
        }
        try {
            Produit produit = produitTransformer.toEntity(dto);
            log.info("Successfully created product with name: {}", dto.nom());
            return produitTransformer.toDto(produitDao.save(produit));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating product with name: {}", dto.nom(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the product."+ ex);
        }
    }

    public ProduitPostDto save(ProduitPostDto dto) {
        log.info("Creating new product with name: {}", dto.nom());

        if(produitDao.findByNom(dto.nom()).isPresent())
        {
            log.warn("Attempted to create a duplicate product with name: {}", dto.nom());
            throw new RuntimeException("This product name already exists");
        }
        try {
            Produit produit = produitTransformer.toEntityPost(dto);
            log.info("Successfully created product with name: {}", dto.nom());
            return produitTransformer.toDtoPost(produitDao.save(produit));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating product with name: {}", dto.nom(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the product."+ ex);
        }
    }

    public ProduitPostDto saveProductBySeller(ProduitPostDto dto, UserDto sellerDto) {
        log.info("Creating new product with name: {}", dto.nom());

        if (produitDao.findByNom(dto.nom()).isPresent()) {
            log.warn("Attempted to create a duplicate product with name: {}", dto.nom());
            throw new RuntimeException("This product name already exists");
        }
        try {
            Produit produit = produitTransformer.toEntityPost(dto);
            User seller = userDao.findById(sellerDto.id())
                    .orElseThrow(() -> new RuntimeException("Seller not found"));
            produit.setSeller(seller); // Associate the product with the seller
            log.info("Successfully created product with name: {}", dto.nom());
            return produitTransformer.toDtoPost(produitDao.save(produit));
        } catch (Exception ex) {
            log.error("Error occurred while creating product with name: {}", dto.nom(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the product." + ex);
        }
    }

    @Override
    public ProduitGetDto update(ProduitGetDto dto, Long id) {
        log.info("Updating product with ID: {}", id);
        id = dto.id();
        ProduitGetDto existingProduitDto = findById(id);
        Produit existingProduit = produitTransformer.toEntity(dto);
        existingProduit.setId(dto.id());
        existingProduit.setNom(dto.nom());
        existingProduit.setPrix(dto.prix());
        existingProduit.setDescription(dto.description());
        existingProduit.setQuantite(dto.quantite());
        existingProduit.setImage(dto.image());
        existingProduit.setEtatProduit(dto.etatProduit());
        existingProduit.setStock(dto.stock());
        existingProduit.setVehicule(dto.vehicule());

        log.info("Successfully updated product with ID: {}", dto.id());
        return produitTransformer.toDto(produitDao.save(existingProduit));
    }


    @Override
    public void deleteById(Long id) {
        log.info("Deleting produit with ID: {}", id);
        ProduitGetDto foundProduct = findById(id);
        produitDao.deleteById(foundProduct.id());

        log.info("Successfully deleted product with ID: {}", id);
    }
}
