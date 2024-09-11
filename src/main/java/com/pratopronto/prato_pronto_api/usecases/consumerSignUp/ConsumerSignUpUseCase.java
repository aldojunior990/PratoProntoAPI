package com.pratopronto.prato_pronto_api.usecases.consumerSignUp;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConsumerSignUpUseCase implements UseCaseContract<ConsumerSignUpInput, ResponseEntity<ConsumerSignUpOutput>> {

    @Autowired
    private CustomerGateway customerRepository;

    @Autowired
    private ConsumerGateway consumerRepository;

    @Override
    public ResponseEntity<ConsumerSignUpOutput> execute(ConsumerSignUpInput input) {
        System.out.println(input.toString());
        if (customerRepository.findByEmail(input.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(input.password());
        Customer customer = Customer.create(input.email(), encryptedPassword);
        Consumer consumer = Consumer.with(customer.getId(), input.name(), input.lastName(), input.cpf());

        customerRepository.save(customer);
        consumerRepository.save(consumer);
        return ResponseEntity.ok().build();
    }
}
