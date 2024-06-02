package com.ecom.backend.service.Impl;

import com.ecom.backend.dto.VehiculeDto.VehiculeGetDto;
import com.ecom.backend.dto.VehiculeDto.VehiculePostDto;
import com.ecom.backend.dto.VehiculeDto.VehiculeGetDto;
import com.ecom.backend.entity.Vehicule;
import com.ecom.backend.enums.TypeVehicule;
import com.ecom.backend.repository.VehiculeRepository;
import com.ecom.backend.service.facade.VehiculeService;
import com.ecom.backend.transformer.VehiculeTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehiculeServiceImpl implements VehiculeService {
    private final VehiculeRepository VehiculeDao;
    private final VehiculeTransformer VehiculeTransformer;
    @Override
    public VehiculeGetDto findById(Long id) {
        log.info("Fetching Vehicule by ID: {}", id);
        return VehiculeDao.findById(id)
                .map(VehiculeTransformer::toDto)
                .orElseThrow(() -> {
                    log.error("Vehicule not found with ID: {}", id);
                    return new RuntimeException("Unable to find a Vehicule with the given Id : "+id);
                });
    }

    @Override
    public List<VehiculeGetDto> findAll() {
        log.info("Fetching all Vehicules");
        List<Vehicule> Vehicules=VehiculeDao.findAll();
        if(Vehicules.isEmpty()){
            throw new RuntimeException("List of users is Empty");
        }
        return VehiculeTransformer.toDto(Vehicules);
    }

    public VehiculePostDto save(VehiculePostDto dto) {

        log.info("Creating new Vehicule with name: {}", dto.nomVehicule());
        try {
            Vehicule Vehicule = VehiculeTransformer.toEntityPost(dto);
            log.info("Successfully created Vehicule with name: {}", dto.nomVehicule());
            return VehiculeTransformer.toDtoPost(VehiculeDao.save(Vehicule));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating Vehicule with name: {}", dto.nomVehicule(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the Vehicule."+ ex);
        }
    }

    @Override
    public VehiculeGetDto save(VehiculeGetDto dto) {

        log.info("Creating new Vehicule with name: {}", dto.nomVehicule());

        try {
            Vehicule Vehicule = VehiculeTransformer.toEntity(dto);
            log.info("Successfully created Vehicule with name: {}", dto.nomVehicule());
            return VehiculeTransformer.toDto(VehiculeDao.save(Vehicule));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating Vehicule with name: {}", dto.nomVehicule(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the Vehicule."+ ex);
        }
    }

    @Override
    public VehiculeGetDto update(VehiculeGetDto dto, Long id) {
        id = dto.id();
        VehiculeGetDto existingVehiculeDto = findById(id);
        Vehicule existingVehicule = VehiculeTransformer.toEntity(dto);
        existingVehicule.setId(dto.id());
        existingVehicule.setNomVehicule(dto.nomVehicule());
        existingVehicule.setImage(dto.image());
        existingVehicule.setTypeVehicule(dto.typeVehicule());
        if (existingVehicule.getProduits() != null) {
            existingVehicule.setProduits(dto.produits());
        }

        log.info("Successfully updated Vehicule with ID: {}", dto.id());
        return VehiculeTransformer.toDto(VehiculeDao.save(existingVehicule));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting Vehicule with ID: {}", id);
        VehiculeGetDto foundVehicule = findById(id);
        VehiculeDao.deleteById(foundVehicule.id());

        log.info("Successfully deleted Vehicule with ID: {}", id);
    }
}
