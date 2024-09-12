package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class RestaurantRepository implements RestaurantGateway {
    @Autowired
    private Connection connection;

    @Override
    public void save(Restaurant restaurant) {
        String sql = "insert into restaurante(id, nome, tipo_culinaria) values (?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, restaurant.getId().toString());
            stm.setString(2, restaurant.getName());
            stm.setString(3, restaurant.getCookingType());
            stm.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void update(Restaurant restaurant) {

    }

    @Override
    public Restaurant findByEmail(String email) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
