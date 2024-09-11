package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class CustomerRepository implements CustomerGateway {

    @Autowired
    private Connection connection;

    @Override
    public void save(Customer customer) {
        String sql = "insert into cliente values(?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, customer.getId().toString());
            stm.setString(2, customer.getEmail());
            stm.setString(3, customer.getPassword());
            stm.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public UserDetails findByEmail(String email) {
        String sql = "select * from cliente C where C.email = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql);) {
            stm.setString(1, email);
            try (ResultSet rs = stm.executeQuery();) {
                if (rs.next()) {
                    UUID id = UUID.fromString(rs.getString("id"));
                    String emailFromDB = rs.getString("email");
                    String encodedPassword = rs.getString("senha");
                    return Customer.with(id, emailFromDB, encodedPassword);
                }
            } catch (SQLException err) {
                err.printStackTrace();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }

        return null;
    }
}
