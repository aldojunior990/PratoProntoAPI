package com.pratopronto.prato_pronto_api.usecases.signin;

import com.pratopronto.prato_pronto_api.domain.customer.CustomerDetails;

public record SignInOutputDto(String token, CustomerDetails details) {
}

