package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.address.Address;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantRepository implements RestaurantGateway {
    @Autowired
    private Connection connection;

    @Override
    public Boolean save(Restaurant restaurant) {
        String sql = "insert into restaurante(id, nome, tipo_culinaria) values (?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, restaurant.getId().toString());
            stm.setString(2, restaurant.getName());
            stm.setString(3, restaurant.getCookingType());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(Restaurant restaurant) {
        String sql = "update restaurante set nome=?, tipo_culinaria=? where id=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, restaurant.getName());
            stm.setString(2, restaurant.getCookingType());
            stm.setString(3, restaurant.getId().toString());

            int rowsAffected = stm.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }

    }


    @Override
    public Boolean delete(UUID id) {
        String sql = "DELETE FROM restaurante WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, id.toString());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }


    @Override
    public Restaurant findById(String id) {
        String sql = "select * from restaurante R where R.id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            Restaurant restaurant = null;
            while (rs.next()) {
                String restaurantId = rs.getString("id");
                String name = rs.getString("nome");
                String cookingType = rs.getString("tipo_culinaria");
                Double grade = rs.getDouble("avaliacao");
                restaurant = Restaurant.with(UUID.fromString(restaurantId), name, cookingType, grade);
            }
            rs.close();
            return restaurant;
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Restaurant> findAll() {
        String sql = "select * from restaurante";
        List<Restaurant> restaurants = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String restaurantId = rs.getString("id");
                String name = rs.getString("nome");
                String cookingType = rs.getString("tipo_culinaria");
                Double grade = rs.getDouble("avaliacao");
                Restaurant restaurant = Restaurant.with(UUID.fromString(restaurantId), name, cookingType, grade);
                restaurants.add(restaurant);
            }
            rs.close();
            return restaurants;
        } catch (Exception err) {
            err.printStackTrace();
            return new ArrayList<>();
        }
    }
}
