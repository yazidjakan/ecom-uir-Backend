package com.ecom.backend.transformer;

import com.ecom.backend.dto.StockDto.StockGetDto;
import com.ecom.backend.dto.StockDto.StockPostDto;
import com.ecom.backend.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockTransformer extends AbstractTransformer<Stock, StockGetDto> {
    @Override
    public Stock toEntity(StockGetDto dto) {
        if(dto == null) {
            return null;
        }else{
            Stock entity=new Stock();
            entity.setId(dto.id());
            entity.setNomStock(dto.nomStock());
            entity.setStatutStock(dto.statutStock());
            entity.setProduits(dto.produits());
            return entity;
        }
    }

    @Override
    public StockGetDto toDto(Stock entity) {
        if (entity == null) {
            return null;
        }else{
            StockGetDto dto=new StockGetDto(
                    entity.getId(),
                    entity.getNomStock(),
                    entity.getStatutStock(),
                    entity.getProduits()
            );
            return dto;
        }
    }

    public Stock toEntityPost(StockPostDto dto) {
        if(dto == null) {
            return null;
        }else{
            Stock entity=new Stock();
            entity.setId(dto.id());
            entity.setNomStock(dto.nomStock());
            entity.setStatutStock(dto.statutStock());
            return entity;
        }
    }

    public StockPostDto toDtoPost(Stock entity) {
        if (entity == null) {
            return null;
        }else{
            StockPostDto dto=new StockPostDto(
                    entity.getId(),
                    entity.getNomStock(),
                    entity.getStatutStock());
            return dto;
        }
    }
}


