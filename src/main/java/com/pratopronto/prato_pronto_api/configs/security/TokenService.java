package com.pratopronto.prato_pronto_api.configs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    @Value("${api.security.token.secret}")
    private String secret;

    @Value("{api.security.token.issuer}")
    private String issuer;

    public String generateToken(Customer customer) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer(issuer).withSubject(customer.getEmail()).withExpiresAt(generateExpirationDate()).sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token.", exception);
        }
    }

    public String validateTokenWithEmail(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer(issuer).build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
