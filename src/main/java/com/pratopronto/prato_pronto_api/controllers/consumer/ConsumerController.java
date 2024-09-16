package com.pratopronto.prato_pronto_api.controllers.consumer;

import com.pratopronto.prato_pronto_api.usecases.consumer.getConsumer.GetConsumerUseCase;
import com.pratopronto.prato_pronto_api.usecases.consumer.getConsumer.GetConsumerOutput;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private GetConsumerUseCase getConsumerUseCase;

    @GetMapping
    public ResponseEntity<GetConsumerOutput> getConsumer(HttpServletRequest data) {
        return getConsumerUseCase.execute(data);
    }
}
