package com.pratopronto.prato_pronto_api.usecases.credit_card;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import com.pratopronto.prato_pronto_api.domain.credit_card.CreditCard;
import com.pratopronto.prato_pronto_api.domain.credit_card.CreditCardGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.credit_card.dtos.CreditCardDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FindCreditCardById implements UseCaseContract<HttpRequestDTO<String>, ResponseEntity<CreditCardDTO>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private CreditCardGateway creditCardGateway;

    @Autowired
    private ConsumerGateway consumerGateway;

    @Override
    public ResponseEntity<CreditCardDTO> execute(HttpRequestDTO<String> data) {
        try {
            Customer customer = extractTokenAndReturnCustomer.execute(data.request());

            if (customer == null) return ResponseEntity.badRequest().body(null);

            Consumer consumer = consumerGateway.findByEmail(customer.getEmail());

            CreditCard creditCard = creditCardGateway.findById(data.content(), consumer);

            return ResponseEntity.ok(new CreditCardDTO(creditCard.getId(), creditCard.getToken(), creditCard.getHolder(), creditCard.getFlag(), creditCard.getExpirationDate()));

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
