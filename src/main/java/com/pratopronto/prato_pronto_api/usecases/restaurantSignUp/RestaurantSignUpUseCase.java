package com.pratopronto.prato_pronto_api.usecases.restaurantSignUp;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class RestaurantSignUpUseCase implements UseCaseContract<RestaurantSignUpInput, ResponseEntity<RestaurantSignUpOutput>> {

    @Autowired
    private RestaurantGateway restaurantGateway;

    @Autowired
    private CustomerGateway customerGateway;

    @Override
    public ResponseEntity<RestaurantSignUpOutput> execute(RestaurantSignUpInput input) {
        try {
            String encryptedPassword = new BCryptPasswordEncoder().encode(input.password());

            Customer customer = Customer.create(input.email(), encryptedPassword);

            Restaurant restaurant = Restaurant.with(customer.getId(), input.name(), input.cookingType(), 0.0);

            customerGateway.save(customer);

            restaurantGateway.save(restaurant);

            return ResponseEntity.ok().body(new RestaurantSignUpOutput("Restaurante cadastrado com sucesso"));
        } catch (SQLException err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().
                    body(new RestaurantSignUpOutput("Ocorreu um erro ao tentar cadastrar o restaurante"));
        }
    }
}
