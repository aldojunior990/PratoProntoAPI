package com.pratopronto.prato_pronto_api.usecases;

import jakarta.servlet.http.HttpServletRequest;

public record HttpRequestDTO<T>(
        T content,
        HttpServletRequest request
) {
}
