package com.pratopronto.prato_pronto_api.usecases.credit_card.dtos;

import java.util.Date;
import java.util.UUID;

public record CreditCardDTO(
        UUID id,
        String token,
        String holder,
        String flag,
        String expirationDate
) {
}
