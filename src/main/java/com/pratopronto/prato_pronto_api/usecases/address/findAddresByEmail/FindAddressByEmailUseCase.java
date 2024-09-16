package com.pratopronto.prato_pronto_api.usecases.address.findAddresByEmail;

import com.pratopronto.prato_pronto_api.configs.security.SecurityFilter;
import com.pratopronto.prato_pronto_api.configs.security.TokenService;
import com.pratopronto.prato_pronto_api.domain.address.Address;
import com.pratopronto.prato_pronto_api.domain.address.AddressGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.address.AddressDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindAddressByEmailUseCase implements UseCaseContract<HttpServletRequest, ResponseEntity<List<AddressDTO>>> {

    @Autowired
    private AddressGateway addressGateway;

    @Autowired
    private CustomerGateway customerGateway;

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<List<AddressDTO>> execute(HttpServletRequest input) {

        String token = securityFilter.recoverToken(input);

        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        String email = tokenService.validateTokenAndGetEmail(token);
        Customer customer = (Customer) customerGateway.findByEmail(email);

        if (customer != null) {
            List<AddressDTO> addressByEmail = new ArrayList<>();
            for (Address it : addressGateway.findAllByCostumer(customer)) {
                addressByEmail.add(new AddressDTO(
                        it.getId(),
                        it.getPublicPlace(),
                        it.getNumber(),
                        it.getComplement(),
                        it.getNeighborhood(),
                        it.getState(),
                        it.getCity(),
                        it.getCountry(),
                        it.getCep()
                ));
            }

            return ResponseEntity.ok(addressByEmail);
        }
        return ResponseEntity.internalServerError().body(null);
    }
}
