package com.ecom.backend.transformer;

import com.ecom.backend.dto.VehiculeDto.VehiculeGetDto;
import com.ecom.backend.dto.VehiculeDto.VehiculePostDto;
import com.ecom.backend.entity.Vehicule;
import com.ecom.backend.transformer.AbstractTransformer;
import org.springframework.stereotype.Component;

@Component
public class VehiculeTransformer extends AbstractTransformer<Vehicule, VehiculeGetDto> {
    @Override
    public Vehicule toEntity(VehiculeGetDto dto) {
        if(dto == null) {
            return null;
        }else{
            Vehicule entity=new Vehicule();
            entity.setId(dto.id());
            entity.setNomVehicule(dto.nomVehicule());
            entity.setTypeVehicule(dto.typeVehicule());
            entity.setImage(dto.image());
            entity.setProduits(dto.produits());
            return entity;
        }
    }

    @Override
    public VehiculeGetDto toDto(Vehicule entity) {
        if (entity == null) {
            return null;
        }else{
            VehiculeGetDto dto=new VehiculeGetDto(
                    entity.getId(),
                    entity.getNomVehicule(),
                    entity.getImage(),
                    entity.getTypeVehicule(),
                    entity.getProduits()
            );
            return dto;
        }
    }

    public Vehicule toEntityPost(VehiculePostDto dto) {
        if(dto == null) {
            return null;
        }else{
            Vehicule entity=new Vehicule();
            entity.setId(dto.id());
            entity.setNomVehicule(dto.nomVehicule());
            entity.setImage(dto.image());
            entity.setTypeVehicule(dto.typeVehicule());
            return entity;
        }
    }

    public VehiculePostDto toDtoPost(Vehicule entity) {
        if (entity == null) {
            return null;
        }else{
            VehiculePostDto dto=new VehiculePostDto(
                    entity.getId(),
                    entity.getNomVehicule(),
                    entity.getImage(),
                    entity.getTypeVehicule());
            return dto;
        }
    }
}