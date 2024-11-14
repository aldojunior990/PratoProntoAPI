package com.pratopronto.prato_pronto_api.usecases.products;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.products.Product;
import com.pratopronto.prato_pronto_api.domain.products.ProductGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.products.dtos.ProductDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindProducts implements UseCaseContract<HttpServletRequest, ResponseEntity<List<ProductDTO>>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private RestaurantGateway restaurantGateway;

    @Autowired
    private ProductGateway productGateway;

    @Override
    public ResponseEntity<List<ProductDTO>> execute(HttpServletRequest data) {
        try {

            ArrayList<ProductDTO> response = new ArrayList<>();

            Customer customer = extractTokenAndReturnCustomer.execute(data);

            Restaurant restaurant = restaurantGateway.findById(customer.getId().toString());

            List<Product> products = productGateway.findAllByRestaurant(restaurant);

            for (Product it : products) {
                response.add(new ProductDTO(it.getId(), it.getName(), it.getDescription(), it.getPrice(), it.getState(), restaurant.getId().toString()));
            }

            return ResponseEntity.ok(response);

        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }


    }
}
