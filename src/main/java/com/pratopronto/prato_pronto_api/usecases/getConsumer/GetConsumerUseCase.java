package com.pratopronto.prato_pronto_api.usecases.getConsumer;

import com.pratopronto.prato_pronto_api.configs.security.SecurityFilter;
import com.pratopronto.prato_pronto_api.configs.security.TokenService;
import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetConsumerUseCase implements UseCaseContract<HttpServletRequest, ResponseEntity<GetConsumerOutput>> {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ConsumerGateway consumerGateway;


    @Override
    public ResponseEntity<GetConsumerOutput> execute(HttpServletRequest input) {

        String token = securityFilter.recoverToken(input);

        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        String email = tokenService.validateTokenAndGetEmail(token);

        Consumer consumer = consumerGateway.findByEmail(email);

        System.out.println(consumer);

        if (consumer == null) return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(new GetConsumerOutput(
                consumer.getId(),
                consumer.getName(),
                consumer.getLastName(),
                consumer.getCpf()));
    }

    //teste2
}
