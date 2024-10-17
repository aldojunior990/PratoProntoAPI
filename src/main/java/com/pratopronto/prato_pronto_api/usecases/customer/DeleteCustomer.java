package com.pratopronto.prato_pronto_api.usecases.customer;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomer implements UseCaseContract<HttpServletRequest, ResponseEntity<String>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private CustomerGateway customerGateway;

    @Override
    public ResponseEntity<String> execute(HttpServletRequest httpServletRequest) {

        try {

            Customer customer = extractTokenAndReturnCustomer.execute(httpServletRequest);

            if (customer == null) return ResponseEntity.badRequest().body("Usuario não cadastrado");

            boolean isDeleted = customerGateway.delete(customer.getId().toString());

            if (!isDeleted) return ResponseEntity.badRequest().body("Não foi possivel deletar o usuario");

            return ResponseEntity.ok("Usuario deletado com sucesso");

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }


    }
}
