package com.pratopronto.prato_pronto_api.usecases.address.dtos;

import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public record FindAddressByIdInputDTO(
        UUID id, HttpServletRequest request
) {
}
