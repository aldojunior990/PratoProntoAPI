package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class ConsumerRepository implements ConsumerGateway {
    @Autowired
    private Connection connection;

    @Override
    public void save(Consumer consumer) throws SQLException {
        String sql = "insert into consumidor values(?, ?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, consumer.getId().toString());
        stm.setString(2, consumer.getName());
        stm.setString(3, consumer.getLastName());
        stm.setString(4, consumer.getCpf());
        stm.executeUpdate();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(Consumer consumer) {

    }

    @Override
    public Consumer findByEmail(String email) {
        return null;
    }
}
