package com.pratopronto.prato_pronto_api.usecases.products.dtos;

import java.util.UUID;

public record UpdateProductDTO(
        UUID id,
        String name,
        String description,
        Double price,
        String state
) {
}
