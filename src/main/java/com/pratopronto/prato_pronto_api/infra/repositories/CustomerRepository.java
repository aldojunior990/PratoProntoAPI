package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerDetails;
import com.pratopronto.prato_pronto_api.domain.customer.CustomerGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
    public boolean delete(String id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, id);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
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

    @Override
    public boolean saveNewConsumer(Customer customer, Consumer consumer) {
        String sql = "CALL salva_novo_cliente_consumidor(?,?,?,?,?,?)";
        try (CallableStatement stm = connection.prepareCall(sql)) {
            stm.setString(1, customer.getId().toString());
            stm.setString(2, customer.getEmail());
            stm.setString(3, customer.getPassword());
            stm.setString(4, consumer.getName());
            stm.setString(5, consumer.getLastName());
            stm.setString(6, consumer.getCpf());

            stm.executeUpdate();

            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveNewRestaurant(Customer customer, Restaurant restaurant) {
        String sql = "CALL salva_novo_cliente_restaurante(?,?,?,?,?)";
        try (CallableStatement stm = connection.prepareCall(sql)) {
            stm.setString(1, customer.getId().toString());
            stm.setString(2, customer.getEmail());
            stm.setString(3, customer.getPassword());
            stm.setString(4, restaurant.getName());
            stm.setString(5, restaurant.getCookingType());

            stm.executeUpdate();

            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public CustomerDetails findCustomerDetails(UUID id) {
        String sql = "CALL GetUserDetails(?)";
        try (CallableStatement stm = connection.prepareCall(sql)) {
            stm.setString(1, id.toString());
            CustomerDetails customerDetails = null;
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String customerID = rs.getString("id");
                    String email = rs.getString("email");
                    String username = rs.getString("username");
                    String role = rs.getString("role");
                    customerDetails = new CustomerDetails(customerID, email, username, role);
                }
                rs.close();
                return customerDetails;
            }

        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }
}
