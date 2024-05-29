package com.ecom.backend.service.Impl;

import com.ecom.backend.dto.CommandeDto.CommandeGetDto;
import com.ecom.backend.dto.CommandeDto.CommandePostDto;
import com.ecom.backend.entity.Commande;
import com.ecom.backend.enums.EtatCommande;
import com.ecom.backend.repository.CommandeRepository;
import com.ecom.backend.service.facade.CommandeService;
import com.ecom.backend.transformer.CommandeTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeDao;
    private final CommandeTransformer commandeTransformer;
    @Override
    public CommandeGetDto findById(Long id) {
        log.info("Fetching commande by ID: {}", id);
        return commandeDao.findById(id)
                .map(commandeTransformer::toDto)
                .orElseThrow(() ->{
                    log.error("Commande not found with ID: {}", id);
                    return new RuntimeException("Unable to find a commande with the given Id : "+id);
                });
    }

    @Override
    public List<CommandeGetDto> findAll() {
        List<Commande> commandes=commandeDao.findAll();
        if(commandes.isEmpty()){
            throw new RuntimeException("List of commandes is Empty");
        }
        return commandeTransformer.toDto(commandes);
    }

    @Override
    public CommandeGetDto save(CommandeGetDto dto) {
        try {
            Commande commande = commandeTransformer.toEntity(dto);
            commande.setEtatCommande(EtatCommande.SOUMISE);
            log.info("Successfully created commande");
            return commandeTransformer.toDto(commandeDao.save(commande));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating commande", ex);
            throw new RuntimeException("An unexpected error occurred while creating the commande."+ ex);
        }
    }

    public CommandePostDto save(CommandePostDto dto) {
        try {
            Commande commande = commandeTransformer.toEntityPost(dto);
            commande.setEtatCommande(EtatCommande.SOUMISE);
            log.info("Successfully created commande");
            return commandeTransformer.toDtoPost(commandeDao.save(commande));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating commande", ex);
            throw new RuntimeException("An unexpected error occurred while creating the commande."+ ex);
        }
    }

    @Override
    public CommandeGetDto update(CommandeGetDto dto, Long id) {
        id = dto.id();
        CommandeGetDto existingCommandeDto = findById(id);
        Commande existingCommande = commandeTransformer.toEntity(dto);
        existingCommande.setId(dto.id());
        existingCommande.setUser(dto.user());
        existingCommande.setEtatCommande(dto.etatCommande());

        log.info("Successfully updated commande with ID: {}", dto.id());
        return commandeTransformer.toDto(commandeDao.save(existingCommande));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting commande with ID: {}", id);
        CommandeGetDto foundCommande = findById(id);
        commandeDao.deleteById(foundCommande.id());

        log.info("Successfully deleted commande with ID: {}", id);
    }
}
