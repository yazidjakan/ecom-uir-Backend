package com.ecom.backend.service.Impl;
import com.ecom.backend.entity.Avis;
import com.ecom.backend.repository.AvisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvisServiceImpl {
    private final AvisRepository avisRepository;

    public List<Avis> findByProduitId(Long produitId) {
        return avisRepository.findByProduitId(produitId);
    }

    public Avis save(Avis avis) {
        return avisRepository.save(avis);
    }
}