package com.pratopronto.prato_pronto_api.usecases.address;

import java.util.UUID;

public record AddressDTO(
        UUID id,
        String publicPlace,
        Integer number,
        String complement,
        String neighborhood,
        String state,
        String city,
        String country,
        String cep
) {
}
