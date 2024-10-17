package com.pratopronto.prato_pronto_api.domain.restaurant;

import java.util.List;

public interface RestaurantGateway {

    Boolean save(Restaurant restaurant);

    Boolean update(Restaurant restaurant);

    Restaurant findById(String id);

    List<Restaurant> findAll();

}
