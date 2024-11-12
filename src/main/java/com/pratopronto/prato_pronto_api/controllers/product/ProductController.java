package com.pratopronto.prato_pronto_api.controllers.product;


import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.products.*;
import com.pratopronto.prato_pronto_api.usecases.products.dtos.ProductDTO;
import com.pratopronto.prato_pronto_api.usecases.products.dtos.SaveProductDTO;
import com.pratopronto.prato_pronto_api.usecases.products.dtos.UpdateProductDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private SaveProduct saveProduct;

    @Autowired
    private UpdateProduct updateProduct;

    @Autowired
    private DeleteProduct deleteProduct;

    @Autowired
    private FindProducts findProducts;

    @Autowired
    private FindProductsByRestaurantID findProductsByRestaurantID;

    @Autowired
    private FindProductByID findProductByID;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody SaveProductDTO saveProductDTO, HttpServletRequest httpServletRequest) {
        return saveProduct.execute(new HttpRequestDTO<>(saveProductDTO, httpServletRequest));
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody UpdateProductDTO updateProductDTO, HttpServletRequest httpServletRequest) {
        return updateProduct.execute(new HttpRequestDTO<>(updateProductDTO, httpServletRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id, HttpServletRequest httpServletRequest) {
        return deleteProduct.execute(new HttpRequestDTO<>(id, httpServletRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(HttpServletRequest httpServletRequest) {
        return findProducts.execute(httpServletRequest);
    }

    @GetMapping("/{restaurantId}/products")
    public ResponseEntity<List<ProductDTO>> findAllByRestaurantID(@PathVariable String restaurantId) {
        return findProductsByRestaurantID.execute(restaurantId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getByID(@PathVariable String id) {
        return findProductByID.execute(id);
    }

}
