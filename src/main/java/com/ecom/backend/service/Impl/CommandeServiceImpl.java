package com.ecom.backend.service.Impl;

import com.ecom.backend.dto.CommandeDto.CommandeGetDto;
import com.ecom.backend.dto.CommandeDto.CommandePostDto;
import com.ecom.backend.entity.Commande;
import com.ecom.backend.entity.User;
import com.ecom.backend.enums.EtatCommande;
import com.ecom.backend.repository.CommandeRepository;
import com.ecom.backend.repository.UserRepository;
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
    private final UserRepository userDao;
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
        Commande existingCommande = commandeDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id: " + id));

        Commande updatedCommande = commandeTransformer.toEntity(dto);
        updatedCommande.setId(existingCommande.getId()); // Ensure we keep the same ID

        updatedCommande = commandeDao.save(updatedCommande);
        return commandeTransformer.toDto(updatedCommande);
    }


    @Override
    public void deleteById(Long id) {
        log.info("Deleting commande with ID: {}", id);
        CommandeGetDto foundCommande = findById(id);
        commandeDao.deleteById(foundCommande.id());

        log.info("Successfully deleted commande with ID: {}", id);
    }
}
