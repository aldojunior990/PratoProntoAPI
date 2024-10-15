package com.pratopronto.prato_pronto_api.usecases.contact.dtos;

import jakarta.servlet.http.HttpServletRequest;

public record HttpRequestInputDTO<T>(
        T content,
        HttpServletRequest request
) {
}
