package com.pratopronto.prato_pronto_api.usecases.orders.dtos;

public record OrderItemDTO(
        String productID, String observations, Integer amount
) {
}
