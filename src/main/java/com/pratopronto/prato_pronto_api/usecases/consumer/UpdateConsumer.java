package com.pratopronto.prato_pronto_api.usecases.consumer;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.consumer.dtos.UpdateConsumerDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateConsumer implements UseCaseContract<HttpRequestDTO<UpdateConsumerDTO>, ResponseEntity<String>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private ConsumerGateway consumerGateway;

    @Override
    public ResponseEntity<String> execute(HttpRequestDTO<UpdateConsumerDTO> data) {
        try {
            Customer customer = extractTokenAndReturnCustomer.execute(data.request());

            if (customer == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            boolean isUpdated = consumerGateway.save(Consumer.with(customer.getId(), data.content().name(), data.content().lastName(), data.content().cpf()));

            if (!isUpdated) return ResponseEntity.badRequest().body("Não foi possivel atualizar o consumidor");

            return ResponseEntity.ok("Consumidor atualizado com sucesso");

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
