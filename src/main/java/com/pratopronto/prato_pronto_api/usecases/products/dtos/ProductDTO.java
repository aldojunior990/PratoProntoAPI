package com.pratopronto.prato_pronto_api.usecases.products.dtos;

import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;

import java.util.UUID;

public record ProductDTO(
        UUID id,

        String name,

        String description,

        Double price,

        String state,

        String restaurantID
) {
}
