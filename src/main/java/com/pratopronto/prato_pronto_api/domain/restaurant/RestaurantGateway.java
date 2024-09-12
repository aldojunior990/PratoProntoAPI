package com.pratopronto.prato_pronto_api.domain.restaurant;

import java.sql.SQLException;
import java.util.UUID;

public interface RestaurantGateway {

    void save(Restaurant restaurant) throws SQLException;

    void update(Restaurant restaurant) throws SQLException;

    Restaurant findByEmail(String email) throws SQLException;

    void delete(UUID id) throws SQLException;
}
