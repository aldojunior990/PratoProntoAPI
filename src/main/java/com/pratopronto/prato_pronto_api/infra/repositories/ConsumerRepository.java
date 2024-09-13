package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(Consumer consumer) {

    }

    @Override
    public Consumer findByEmail(String email) {
        String sql = "select CO.id, CO.nome, CO.sobrenome, CO.cpf from consumidor CO inner join Cliente CL on CL.email = ? and CL.id = CO.id; ";
        try (PreparedStatement stm = connection.prepareStatement(sql);) {
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String name = rs.getString("nome");
                String lastname = rs.getString("sobrenome");
                String cpf = rs.getString("cpf");
                rs.close();
                return Consumer.with(id, name, lastname, cpf);
            }
            return null;
        } catch (SQLException err) {
            return null;
        }
    }
}
