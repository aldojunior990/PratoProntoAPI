package com.pratopronto.prato_pronto_api.usecases.products;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.products.Product;
import com.pratopronto.prato_pronto_api.domain.products.ProductGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.products.dtos.UpdateProductDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class UpdateProduct implements UseCaseContract<HttpRequestDTO<UpdateProductDTO>, ResponseEntity<String>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private RestaurantGateway restaurantGateway;

    @Override
    public ResponseEntity<String> execute(HttpRequestDTO<UpdateProductDTO> data) {
        try {
            Customer customer = extractTokenAndReturnCustomer.execute(data.request());
            Restaurant restaurant = restaurantGateway.findById(customer.getId().toString());

            Product product = Product.with(data.content().id(), data.content().name(), data.content().description(), data.content().price(), data.content().state(), restaurant);

            boolean isUpdated = productGateway.update(product);

            if (!isUpdated) return ResponseEntity.badRequest().body("Não foi possivel atualizar o produto");
            return ResponseEntity.ok("Produto atualizado com sucesso");
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
