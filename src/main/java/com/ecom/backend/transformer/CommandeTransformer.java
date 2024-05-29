package com.ecom.backend.transformer;

import com.ecom.backend.dto.CommandeDto.CommandeGetDto;
import com.ecom.backend.dto.CommandeDto.CommandePostDto;
import com.ecom.backend.entity.Commande;
import org.springframework.stereotype.Component;

@Component
public class CommandeTransformer extends AbstractTransformer<Commande, CommandeGetDto> {
    @Override
    public Commande toEntity(CommandeGetDto dto) {
        if(dto == null) {
            return null;
        }else{
            Commande entity=new Commande();
            entity.setId(dto.id());
            entity.setUser(dto.user());
            entity.setEtatCommande(dto.etatCommande());
            return entity;
        }
    }

    @Override
    public CommandeGetDto toDto(Commande entity) {
        if (entity == null) {
            return null;
        }else{
            CommandeGetDto dto=new CommandeGetDto(
                    entity.getId(),
                    entity.getUser(),
                    entity.getEtatCommande()
            );
            return dto;
        }
    }

    public Commande toEntityPost(CommandePostDto dto) {
        if(dto == null) {
            return null;
        }else{
            Commande entity=new Commande();
            entity.setId(dto.id());
            entity.setEtatCommande(dto.etatCommande());
            return entity;
        }
    }


    public CommandePostDto toDtoPost(Commande entity) {
        if (entity == null) {
            return null;
        }else{
            CommandePostDto dto=new CommandePostDto(
                    entity.getId(),
                    entity.getEtatCommande()
            );
            return dto;
        }
    }
}
