package com.pratopronto.prato_pronto_api.usecases.products;

import com.pratopronto.prato_pronto_api.domain.products.Product;
import com.pratopronto.prato_pronto_api.domain.products.ProductGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.products.dtos.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FindProductByID implements UseCaseContract<String, ResponseEntity<ProductDTO>> {

    @Autowired
    private ProductGateway productGateway;

    @Override
    public ResponseEntity<ProductDTO> execute(String data) {
        try {

            Product product = productGateway.findById(data);

            if (product.getRestaurant() == null) return ResponseEntity.badRequest().build();

            return ResponseEntity.ok(new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getState(), product.getRestaurant().getId().toString()));

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
