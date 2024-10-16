package com.pratopronto.prato_pronto_api.domain.restaurant;

import java.util.List;
import java.util.UUID;

public interface RestaurantGateway {

    Boolean save(Restaurant restaurant);

    Boolean update(Restaurant restaurant);

    Boolean delete(UUID id);

    Restaurant findById(String id);

    List<Restaurant> findAll();

}
