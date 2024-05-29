package com.ecom.backend.service.Impl;

import com.ecom.backend.dto.CategorieDto.CategorieGetDto;
import com.ecom.backend.dto.CategorieDto.CategoriePostDto;
import com.ecom.backend.entity.Categorie;
import com.ecom.backend.repository.CategorieRepository;
import com.ecom.backend.service.facade.CategorieService;
import com.ecom.backend.transformer.CategorieTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieDao;
    private final CategorieTransformer categorieTransformer;
    @Override
    public CategorieGetDto findById(Long id) {
        log.info("Fetching category by ID: {}", id);
        return categorieDao.findById(id)
                .map(categorieTransformer::toDto)
                .orElseThrow(() ->{
                    log.error("category not found with ID: {}", id);
                    return new RuntimeException("Unable to find a category with the given Id : "+id);
                });
    }

    @Override
    public List<CategorieGetDto> findAll() {
        List<Categorie> categories=categorieDao.findAll();
        if(categories.isEmpty()){
            throw new RuntimeException("List of category is Empty");
        }
        return categorieTransformer.toDto(categories);
    }

    @Override
    public CategorieGetDto save(CategorieGetDto dto) {

        log.info("Creating new category with name: {}", dto.nom());

        if(categorieDao.findByNom(dto.nom()).isPresent())
        {
            log.warn("Attempted to create a duplicate category with name: {}", dto.nom());
            throw new RuntimeException("This category name already exists");
        }
        try {
            Categorie categorie = categorieTransformer.toEntity(dto);
            log.info("Successfully created category with name: {}", dto.nom());
            return categorieTransformer.toDto(categorieDao.save(categorie));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating category with name: {}", dto.nom(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the category."+ ex);
        }
    }

    public CategoriePostDto save(CategoriePostDto dto) {

        log.info("Creating new category with name: {}", dto.nom());

        if(categorieDao.findByNom(dto.nom()).isPresent())
        {
            log.warn("Attempted to create a duplicate category with name: {}", dto.nom());
            throw new RuntimeException("This category name already exists");
        }
        try {
            Categorie categorie = categorieTransformer.toEntityPost(dto);
            log.info("Successfully created category with name: {}", dto.nom());
            return categorieTransformer.toDtoPost(categorieDao.save(categorie));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating category with name: {}", dto.nom(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the category."+ ex);
        }
    }

    @Override
    public CategorieGetDto update(CategorieGetDto dto, Long id) {
        id = dto.id();
        CategorieGetDto existingCategorieDto = findById(id);
        Categorie existingCategorie = categorieTransformer.toEntity(dto);
        existingCategorie.setId(dto.id());
        existingCategorie.setNom(dto.nom());
        existingCategorie.setProduits(dto.produits());

        log.info("Successfully updated category with ID: {}", dto.id());
        return categorieTransformer.toDto(categorieDao.save(existingCategorie));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting category with ID: {}", id);
        CategorieGetDto foundCategory = findById(id);
        categorieDao.deleteById(foundCategory.id());

        log.info("Successfully deleted category with ID: {}", id);
    }
}
