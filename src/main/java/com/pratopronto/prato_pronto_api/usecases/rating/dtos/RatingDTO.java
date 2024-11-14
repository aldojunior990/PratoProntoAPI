package com.pratopronto.prato_pronto_api.usecases.rating.dtos;


import java.util.UUID;

public record RatingDTO(
        UUID id,
        String feedback,
        Double nota,
        String restaurantId,
        String consumerId
) {

}
