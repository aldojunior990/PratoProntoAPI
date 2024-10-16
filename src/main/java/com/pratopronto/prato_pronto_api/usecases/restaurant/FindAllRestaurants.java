package com.pratopronto.prato_pronto_api.usecases.restaurant;

import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.restaurant.dtos.RestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindAllRestaurants implements UseCaseContract<Integer, ResponseEntity<List<RestaurantDTO>>> {


    @Autowired
    private RestaurantGateway restaurantGateway;

    @Override
    public ResponseEntity<List<RestaurantDTO>> execute(Integer integer) {
        try {
            ArrayList<RestaurantDTO> response = new ArrayList<>();
            List<Restaurant> restaurants = restaurantGateway.findAll();

            for (Restaurant it : restaurants) {
                response.add(RestaurantDTO.toRestaurantDTO(it));
            }

            return ResponseEntity.ok(response);

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
