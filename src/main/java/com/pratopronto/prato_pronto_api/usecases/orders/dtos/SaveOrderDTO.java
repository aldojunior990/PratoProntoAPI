package com.pratopronto.prato_pronto_api.usecases.orders.dtos;

import com.pratopronto.prato_pronto_api.domain.order.OrderItem;

import java.util.List;

public record SaveOrderDTO(
        String paymentMethod,

        String restaurantID,

        List<OrderItem> items
) {
}
