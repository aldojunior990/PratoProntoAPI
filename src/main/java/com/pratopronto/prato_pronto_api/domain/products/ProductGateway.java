package com.pratopronto.prato_pronto_api.domain.products;

import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;

import java.util.List;

public interface ProductGateway {

    boolean save(Product product);

    boolean update(Product product);

    boolean delete(Product product);

    List<Product> findAllByRestaurant(Restaurant restaurant);

    Product findById(String id);
}
