package com.ecom.backend.service.facade;

import com.ecom.backend.dto.ProduitDto.ProduitGetDto;
import com.ecom.backend.entity.Produit;

import java.util.List;

public interface ProduitService extends AbstractService<ProduitGetDto, Long> {
    List<Produit> getProductsBySeller(Long sellerId);
}
