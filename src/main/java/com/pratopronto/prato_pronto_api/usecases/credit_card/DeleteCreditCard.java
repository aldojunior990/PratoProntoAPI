package com.pratopronto.prato_pronto_api.usecases.credit_card;

import com.pratopronto.prato_pronto_api.domain.credit_card.CreditCardGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteCreditCard implements UseCaseContract<String, ResponseEntity<String>> {

    @Autowired
    private CreditCardGateway creditCardGateway;

    @Override
    public ResponseEntity<String> execute(String id) {
        try {
            boolean isDeleted = creditCardGateway.delete(id);
            if (!isDeleted) return ResponseEntity.badRequest().body("Não foi possivel deletar");
            return ResponseEntity.ok("Cartão de credito deletado com sucesso");
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
}
