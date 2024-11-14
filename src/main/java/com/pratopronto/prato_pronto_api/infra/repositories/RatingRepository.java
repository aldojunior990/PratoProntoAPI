package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.rating.Rating;
import com.pratopronto.prato_pronto_api.domain.rating.RatingGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class RatingRepository implements RatingGateway {

    @Autowired
    private Connection connection;

    @Override
    public boolean save(Rating rating) {
        String sql = "INSERT INTO avaliacao (id, feedback, nota, data_hora, id_restaurante, id_consumidor) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, rating.getId().toString());
            stm.setString(2, rating.getFeedback());
            stm.setDouble(3, rating.getNota());
            stm.setDate(4, new Date(0));
            stm.setString(5, rating.getRestaurantID());
            stm.setString(6, rating.getConsumerID());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }



    @Override
    public List<Rating> findAllByRestaurant(String restaurantID) {
        String sql = "SELECT * FROM avaliacao WHERE id_restaurante = ?";
        List<Rating> ratings = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, restaurantID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("id");
                String Feedback = rs.getString("feedback");
                Double nota = rs.getDouble("nota");
                String id_restaurante = rs.getString("id_restaurante");
                String id_consumidor = rs.getString("id_consumidor");
                ratings.add(Rating.with(UUID.fromString(ID), Feedback, nota, id_restaurante, id_consumidor));
            }
            rs.close();
            return ratings;
        } catch (Exception err) {
            err.printStackTrace();
            return new ArrayList<>();
        }
    }




}