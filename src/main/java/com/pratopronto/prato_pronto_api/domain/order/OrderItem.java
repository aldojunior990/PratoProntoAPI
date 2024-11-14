package com.pratopronto.prato_pronto_api.domain.order;

public record OrderItem(
        String orderID, String productID, String observations, Integer amount, Double subtotal
){}
