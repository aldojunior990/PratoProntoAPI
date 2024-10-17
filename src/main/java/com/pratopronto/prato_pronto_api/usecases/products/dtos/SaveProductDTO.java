package com.pratopronto.prato_pronto_api.usecases.products.dtos;

public record SaveProductDTO(
        String name,
        String description,
        Double price,
        String state
) {
}
