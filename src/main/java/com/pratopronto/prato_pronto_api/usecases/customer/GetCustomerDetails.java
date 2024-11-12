package com.pratopronto.prato_pronto_api.usecases.customer;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerDetails;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class GetCustomerDetails implements UseCaseContract<HttpServletRequest, ResponseEntity<CustomerDetails>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private CustomerGateway customerGateway;

    @Override
    public ResponseEntity<CustomerDetails> execute(HttpServletRequest httpServletRequest) {
        try {

            Customer customer = extractTokenAndReturnCustomer.execute(httpServletRequest);

            if (customer == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            CustomerDetails details = customerGateway.findCustomerDetails(customer.getId());

            return ResponseEntity.ok(details);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
