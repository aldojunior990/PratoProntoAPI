package com.pratopronto.prato_pronto_api.usecases.products;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.products.Product;
import com.pratopronto.prato_pronto_api.domain.products.ProductGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteProduct implements UseCaseContract<HttpRequestDTO<String>, ResponseEntity<String>> {

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private RestaurantGateway restaurantGateway;

    @Override
    public ResponseEntity<String> execute(HttpRequestDTO<String> data) {
        try {
            Customer customer = extractTokenAndReturnCustomer.execute(data.request());
            Product product = productGateway.findById(data.content());
            Restaurant restaurant = restaurantGateway.findById(customer.getId().toString());

            if (product == null) return ResponseEntity.badRequest().body("Produto não existe");

            boolean isDeleted = productGateway.delete(Product.with(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getState(), restaurant));

            if (!isDeleted) return ResponseEntity.badRequest().body("Não foi possivel deletar o produto");

            return ResponseEntity.ok("Produto deletado com sucesso");

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
