package com.pratopronto.prato_pronto_api.usecases.address.dtos;

import jakarta.servlet.http.HttpServletRequest;

public record FindAddressInputDTO(
        AddressDTO address, HttpServletRequest request
) {
}
