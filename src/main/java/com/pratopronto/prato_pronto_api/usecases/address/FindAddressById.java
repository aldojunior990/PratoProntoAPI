package com.pratopronto.prato_pronto_api.usecases.address;

import com.pratopronto.prato_pronto_api.configs.security.SecurityFilter;
import com.pratopronto.prato_pronto_api.configs.security.TokenService;
import com.pratopronto.prato_pronto_api.domain.address.Address;
import com.pratopronto.prato_pronto_api.domain.address.AddressGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.infra.repositories.AddressRepository;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.address.dtos.AddressDTO;
import com.pratopronto.prato_pronto_api.usecases.address.dtos.FindAddressByIdInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FindAddressById implements UseCaseContract<FindAddressByIdInputDTO, ResponseEntity<AddressDTO>> {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CustomerGateway customerGateway;

    @Autowired
    private AddressGateway addressGateway;

    @Override
    public ResponseEntity<AddressDTO> execute(FindAddressByIdInputDTO data) {
        try {
            String token = securityFilter.recoverToken(data.request());

            if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            String email = tokenService.validateTokenAndGetEmail(token);
            Customer customer = (Customer) customerGateway.findByEmail(email);

            Address address = addressGateway.findById(data.id(), customer);
            if (address == null) return ResponseEntity.ok(null);
            return ResponseEntity.ok(AddressDTO.toAddressDto(address));

        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
