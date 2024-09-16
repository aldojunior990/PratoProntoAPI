package com.pratopronto.prato_pronto_api.usecases.address.saveNewAddress;

import com.pratopronto.prato_pronto_api.configs.security.SecurityFilter;
import com.pratopronto.prato_pronto_api.configs.security.TokenService;
import com.pratopronto.prato_pronto_api.domain.address.Address;
import com.pratopronto.prato_pronto_api.domain.address.AddressGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaveNewAddressUseCase implements UseCaseContract<SaveNewAddressInput, ResponseEntity<SaveNewAddressOutput>> {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CustomerGateway customerGateway;

    @Autowired
    private AddressGateway addressGateway;


    @Override
    public ResponseEntity<SaveNewAddressOutput> execute(SaveNewAddressInput input) {
        String token = securityFilter.recoverToken(input.request());

        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        String email = tokenService.validateTokenAndGetEmail(token);
        Customer customer = (Customer) customerGateway.findByEmail(email);

        if (customer != null) {
            try {
                Address address = Address.create(
                        input.address().publicPlace(),
                        input.address().number(),
                        input.address().complement(),
                        input.address().neighborhood(),
                        input.address().city(),
                        input.address().state(),
                        input.address().country(),
                        input.address().cep(),
                        customer);

                addressGateway.save(address);

                return ResponseEntity.ok(new SaveNewAddressOutput("Endereço cadastrado com sucesso"));
            } catch (Exception err) {
                err.printStackTrace();
                return ResponseEntity.internalServerError().body(new SaveNewAddressOutput(err.getMessage()));
            }
        }

        return ResponseEntity.internalServerError().body(new SaveNewAddressOutput("Ocorreu um erro ao inserir o endereço"));
    }
}
