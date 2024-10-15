package com.pratopronto.prato_pronto_api.utils;

import com.pratopronto.prato_pronto_api.configs.security.SecurityFilter;
import com.pratopronto.prato_pronto_api.configs.security.TokenService;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractTokenAndReturnCustomer {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CustomerGateway customerGateway;

    public Customer execute(HttpServletRequest data) {
        String token = securityFilter.recoverToken(data);
        if (token == null) return null;
        String email = tokenService.validateTokenAndGetEmail(token);
        return (Customer) customerGateway.findByEmail(email);
    }
}
