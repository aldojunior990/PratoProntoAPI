package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ConsumerRepository implements ConsumerGateway {
    @Autowired
    private Connection connection;

    @Override
    public void save(Consumer consumer) {
        String sql = "insert into consumidor values(?, ?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, consumer.getId().toString());
            stm.setString(2, consumer.getName());
            stm.setString(3, consumer.getLastName());
            stm.setString(4, consumer.getCpf());
            stm.executeUpdate();

        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
}
