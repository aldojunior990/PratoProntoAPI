package com.pratopronto.prato_pronto_api.usecases.products;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.products.Product;
import com.pratopronto.prato_pronto_api.domain.products.ProductGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.products.dtos.SaveProductDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaveProduct implements UseCaseContract<HttpRequestDTO<SaveProductDTO>, ResponseEntity<String>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private RestaurantGateway restaurantGateway;

    @Override
    public ResponseEntity<String> execute(HttpRequestDTO<SaveProductDTO> data) {
        try {
            Customer customer = extractTokenAndReturnCustomer.execute(data.request());

            Restaurant restaurant = restaurantGateway.findById(customer.getId().toString());

            Product product = Product.create(data.content().name(), data.content().description(), data.content().price(), data.content().state(), restaurant);

            boolean isSaved = productGateway.save(product);

            if (!isSaved) return ResponseEntity.badRequest().body("Não foi possivel salvar o produto");

            return ResponseEntity.ok("Produto salvo com sucesso");

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }


    }
}
