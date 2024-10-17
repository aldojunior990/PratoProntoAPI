package com.pratopronto.prato_pronto_api.usecases.consumer;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.consumer.dtos.ConsumerSignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConsumerSignUpUseCase implements UseCaseContract<ConsumerSignUpDTO, ResponseEntity<String>> {

    @Autowired
    private CustomerGateway customerRepository;

    @Autowired
    private ConsumerGateway consumerRepository;

    @Override
    public ResponseEntity<String> execute(ConsumerSignUpDTO input) {
        try {
            if (customerRepository.findByEmail(input.email()) != null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Consumidor já cadastrado");

            String encryptedPassword = new BCryptPasswordEncoder().encode(input.password());

            Customer customer = Customer.create(input.email(), encryptedPassword);

            Consumer consumer = Consumer.with(customer.getId(), input.name(), input.lastName(), input.cpf());

            customerRepository.save(customer);

            consumerRepository.save(consumer);

            return ResponseEntity.ok().build();

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }


    }
}
