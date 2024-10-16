package com.pratopronto.prato_pronto_api.usecases.restaurant;

import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.restaurant.dtos.RestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class FindRestaurantById implements UseCaseContract<String, ResponseEntity<RestaurantDTO>> {

    @Autowired
    private RestaurantGateway restaurantGateway;

    @Override
    public ResponseEntity<RestaurantDTO> execute(String id) {
        try {
            Restaurant restaurant = restaurantGateway.findById(id);
            return ResponseEntity.ok(RestaurantDTO.toRestaurantDTO(restaurant));
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
