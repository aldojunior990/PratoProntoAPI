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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaveCreditCard implements UseCaseContract<HttpRequestDTO<CreditCardDTO>, ResponseEntity<String>> {


    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private CreditCardGateway creditCardGateway;

    @Autowired
    private ConsumerGateway consumerGateway;

    @Override
    public ResponseEntity<String> execute(HttpRequestDTO<CreditCardDTO> data) {
        try {
            Customer customer = extractTokenAndReturnCustomer.execute(data.request());
            if (customer == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            Consumer consumer = consumerGateway.findByEmail(customer.getEmail());

            boolean isSaved = creditCardGateway.save(CreditCard.create(
                    data.content().token(), data.content().holder(), data.content().flag(), data.content().expirationDate(), consumer));

            if (!isSaved) return ResponseEntity.badRequest().build();

            return ResponseEntity.ok("Cartão de credito cadastrado com sucesso");

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
}
