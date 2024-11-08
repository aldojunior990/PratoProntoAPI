package com.pratopronto.prato_pronto_api.usecases.products;

import com.pratopronto.prato_pronto_api.domain.products.Product;
import com.pratopronto.prato_pronto_api.domain.products.ProductGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.products.dtos.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindProductsByRestaurantID implements UseCaseContract<String, ResponseEntity<List<ProductDTO>>> {

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private RestaurantGateway restaurantGateway;

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String data) {
        try {
            ArrayList<ProductDTO> response = new ArrayList<>();

            Restaurant restaurant = restaurantGateway.findById(data);

            if (restaurant == null) return ResponseEntity.badRequest().build();

            List<Product> products = productGateway.findAllByRestaurant(restaurant);

            for (Product it : products) {
                response.add(new ProductDTO(it.getId(), it.getName(), it.getDescription(), it.getPrice(), it.getState(), it.getRestaurant().getId().toString()));
            }

            return ResponseEntity.ok(response);

        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
