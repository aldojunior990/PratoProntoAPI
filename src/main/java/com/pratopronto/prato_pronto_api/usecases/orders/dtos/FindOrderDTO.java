package com.pratopronto.prato_pronto_api.usecases.orders.dtos;

import java.util.Date;

public record FindOrderDTO(
        String id,
        Date datetime,

        String status,

        Double totalPrice,

        String customerID,

        String restaurantID,

        String restaurantName

) {
}
