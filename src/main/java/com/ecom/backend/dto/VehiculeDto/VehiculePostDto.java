package com.ecom.backend.dto.VehiculeDto;

import com.ecom.backend.enums.TypeVehicule;

public record VehiculePostDto(
        Long id,
        String nomVehicule,
        String image,
        TypeVehicule typeVehicule
) {
}
