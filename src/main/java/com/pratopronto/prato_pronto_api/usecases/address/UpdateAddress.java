package com.pratopronto.prato_pronto_api.usecases.address;


import com.pratopronto.prato_pronto_api.configs.security.SecurityFilter;
import com.pratopronto.prato_pronto_api.configs.security.TokenService;
import com.pratopronto.prato_pronto_api.domain.address.Address;
import com.pratopronto.prato_pronto_api.domain.address.AddressGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.address.dtos.FindAddressInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateAddress implements UseCaseContract<FindAddressInputDTO, ResponseEntity<String>> {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CustomerGateway customerGateway;

    @Autowired
    private AddressGateway addressGateway;

    @Override
    public ResponseEntity<String> execute(FindAddressInputDTO data) {
        String token = securityFilter.recoverToken(data.request());

        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        String email = tokenService.validateTokenAndGetEmail(token);
        Customer customer = (Customer) customerGateway.findByEmail(email);

        if (customer != null) {
            try {
                Address address = data.address().toAddress(customer);
                Boolean isSuccessful = addressGateway.update(address);
                if (!isSuccessful) ResponseEntity.badRequest().body("Não foi possivel atualizar o endereço");
                return ResponseEntity.ok("Endereço atualizado com sucesso");
            } catch (Exception err) {
                return ResponseEntity.internalServerError().body(err.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Usuario não cadastrado");
        }
    }
}
