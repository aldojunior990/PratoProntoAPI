package com.pratopronto.prato_pronto_api.usecases.consumer;

import com.pratopronto.prato_pronto_api.configs.security.SecurityFilter;
import com.pratopronto.prato_pronto_api.configs.security.TokenService;
import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.consumer.dtos.ConsumerDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetConsumerUseCase implements UseCaseContract<HttpServletRequest, ResponseEntity<ConsumerDTO>> {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private ConsumerGateway consumerGateway;

    @Override
    public ResponseEntity<ConsumerDTO> execute(HttpServletRequest input) {

        String token = securityFilter.recoverToken(input);

        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        String email = tokenService.validateTokenAndGetEmail(token);

        Consumer consumer = consumerGateway.findByEmail(email);

        if (consumer == null) return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(new ConsumerDTO(
                consumer.getId(),
                consumer.getName(),
                consumer.getLastName(),
                consumer.getCpf()));
    }

    //teste
}
