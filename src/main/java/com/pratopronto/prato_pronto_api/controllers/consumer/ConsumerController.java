package com.pratopronto.prato_pronto_api.controllers.consumer;

import com.pratopronto.prato_pronto_api.usecases.consumer.GetConsumerUseCase;
import com.pratopronto.prato_pronto_api.usecases.consumer.dtos.ConsumerDTO;
import com.pratopronto.prato_pronto_api.usecases.customer.DeleteCustomer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private GetConsumerUseCase getConsumerUseCase;

    @Autowired
    private DeleteCustomer deleteCustomer;

    @GetMapping
    public ResponseEntity<ConsumerDTO> getConsumer(HttpServletRequest data) {
        return getConsumerUseCase.execute(data);
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(HttpServletRequest httpServletRequest) {
        return deleteCustomer.execute(httpServletRequest);
    }
}
