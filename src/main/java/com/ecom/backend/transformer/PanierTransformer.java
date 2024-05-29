package com.ecom.backend.transformer;

import com.ecom.backend.dto.PanierDto.PanierGetDto;
import com.ecom.backend.dto.PanierDto.PanierGetDto;
import com.ecom.backend.dto.PanierDto.PanierPostDto;
import com.ecom.backend.entity.Panier;
import com.ecom.backend.entity.Panier;
import org.springframework.stereotype.Component;

@Component
public class PanierTransformer extends AbstractTransformer<Panier, PanierGetDto> {
    @Override
    public Panier toEntity(PanierGetDto dto) {
        if(dto == null) {
            return null;
        }else{
            Panier entity=new Panier();
            entity.setId(dto.id());
            entity.setItems(dto.items());
            return entity;
        }
    }

    @Override
    public PanierGetDto toDto(Panier entity) {
        if(entity == null) {
            return null;
        }else{
            PanierGetDto dto=new PanierGetDto(
                    entity.getId(),
                    entity.getItems()
            );
            return dto;
        }
    }
    public Panier toEntityPost(PanierPostDto dto) {
        if(dto == null) {
            return null;
        }else{
            Panier entity=new Panier();
            entity.setId(dto.id());
            return entity;
        }
    }


    public PanierPostDto toDtoPost(Panier entity) {
        if(entity == null) {
            return null;
        }else{
            PanierPostDto dto=new PanierPostDto(
                    entity.getId());
            return dto;
        }
    }
}
