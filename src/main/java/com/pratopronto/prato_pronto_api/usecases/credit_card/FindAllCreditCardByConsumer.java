package com.pratopronto.prato_pronto_api.usecases.credit_card;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import com.pratopronto.prato_pronto_api.domain.credit_card.CreditCard;
import com.pratopronto.prato_pronto_api.domain.credit_card.CreditCardGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.credit_card.dtos.CreditCardDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindAllCreditCardByConsumer implements UseCaseContract<HttpServletRequest, ResponseEntity<List<CreditCardDTO>>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private ConsumerGateway consumerGateway;

    @Autowired
    private CreditCardGateway creditCardGateway;

    @Override
    public ResponseEntity<List<CreditCardDTO>> execute(HttpServletRequest httpServletRequest) {
        try {

            Customer customer = extractTokenAndReturnCustomer.execute(httpServletRequest);

            if (customer == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();


            Consumer consumer = consumerGateway.findByEmail(customer.getEmail());

            List<CreditCard> creditCards = creditCardGateway.findAllByConsumer(consumer);

            ArrayList<CreditCardDTO> response = new ArrayList<>();

            for (CreditCard it : creditCards) {
                response.add(new CreditCardDTO(it.getId(), "", it.getHolder(), it.getFlag(), it.getExpirationDate()));
            }

            return ResponseEntity.ok(response);

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
