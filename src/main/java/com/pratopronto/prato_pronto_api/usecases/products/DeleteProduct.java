package com.pratopronto.prato_pronto_api.usecases.products;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.products.Product;
import com.pratopronto.prato_pronto_api.domain.products.ProductGateway;
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

    @Override
    public ResponseEntity<String> execute(HttpRequestDTO<String> data) {
        try {

            Product product = productGateway.findById(data.content());
            Customer customer = extractTokenAndReturnCustomer.execute(data.request());

            if (product == null) return ResponseEntity.badRequest().body("Produto não existe");

            if (product.getRestaurant().getId() != customer.getId())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            boolean isDeleted = productGateway.delete(product);

            if (!isDeleted) return ResponseEntity.badRequest().body("Não foi possivel deletar o produto");

            return ResponseEntity.ok("Produto deletado com sucesso");

        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
