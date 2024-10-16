package com.pratopronto.prato_pronto_api.usecases.restaurant;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.restaurant.dtos.UpdateRestaurantDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UpdateRestaurant implements UseCaseContract<HttpRequestDTO<UpdateRestaurantDTO>, ResponseEntity<String>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private RestaurantGateway restaurantGateway;

    @Override
    public ResponseEntity<String> execute(HttpRequestDTO<UpdateRestaurantDTO> data) {
        try {
            Customer customer = extractTokenAndReturnCustomer.execute(data.request());

            if (customer == null) return ResponseEntity.badRequest().body("Usuario não existe");

            Restaurant restaurant = Restaurant.with(
                    customer.getId(),
                    data.content().name(),
                    data.content().cookingType(), 0.0);

            Boolean isUpdated = restaurantGateway.update(restaurant);

            if (!isUpdated) return ResponseEntity.badRequest().body("Não foi possivel atualizar");

            return ResponseEntity.ok("Restaurante atualizado com sucesso");


        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
