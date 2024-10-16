package com.pratopronto.prato_pronto_api.usecases.restaurant;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.restaurant.dtos.SignUpRestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RestaurantSignUp implements UseCaseContract<SignUpRestaurantDTO, ResponseEntity<String>> {

    @Autowired
    private RestaurantGateway restaurantGateway;

    @Autowired
    private CustomerGateway customerGateway;

    @Override
    public ResponseEntity<String> execute(SignUpRestaurantDTO input) {
        try {
            String encryptedPassword = new BCryptPasswordEncoder().encode(input.password());

            Customer customer = Customer.create(input.email(), encryptedPassword);

            Restaurant restaurant = Restaurant.with(customer.getId(), input.name(), input.cookingType(), 0.0);

            customerGateway.save(customer);

            Boolean isSaved = restaurantGateway.save(restaurant);

            if (!isSaved) return ResponseEntity.badRequest().body("Não foi possivel cadastrar o restaurante");

            return ResponseEntity.ok().body("Restaurante cadastrado com sucesso");
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().
                    body("Ocorreu um erro ao tentar cadastrar o restaurante");
        }
    }
}
