package com.pratopronto.prato_pronto_api.usecases.signin;

import com.pratopronto.prato_pronto_api.configs.security.TokenService;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerDetails;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class SignInUseCase implements UseCaseContract<SignInInputDto, ResponseEntity<SignInOutputDto>> {

    @Autowired
    private CustomerGateway repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<SignInOutputDto> execute(SignInInputDto input) {
        try {
            var customerHasAccount = (Customer) repository.findByEmail(input.email());

            if (customerHasAccount != null) {
                CustomerDetails customerDetails = repository.findCustomerDetails(customerHasAccount.getId());
                var customer = new UsernamePasswordAuthenticationToken(input.email(), input.password());
                var auth = this.authenticationManager.authenticate(customer);
                var token = tokenService.generateToken((Customer) auth.getPrincipal());
                return ResponseEntity.ok(new SignInOutputDto(token, customerDetails));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
