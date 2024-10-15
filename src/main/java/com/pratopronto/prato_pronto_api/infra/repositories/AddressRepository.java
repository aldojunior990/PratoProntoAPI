package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.address.Address;
import com.pratopronto.prato_pronto_api.domain.address.AddressGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class AddressRepository implements AddressGateway {

    @Autowired
    private Connection connection;

    @Override
    public void save(Address address) {
        String sql = "insert into endereco values(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, address.getId().toString());
            stm.setString(2, address.getPublicPlace());
            stm.setInt(3, address.getNumber());
            stm.setString(4, address.getComplement());
            stm.setString(5, address.getNeighborhood());
            stm.setString(6, address.getCity());
            stm.setString(7, address.getState());
            stm.setString(8, address.getCountry());
            stm.setString(9, address.getCep());
            stm.setString(10, address.getCustomer().getId().toString());
            stm.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public Boolean update(Address address) {

        String sql = "UPDATE endereco SET logradouro = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, cep = ?, id_cliente = ? WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, address.getPublicPlace());
            stm.setInt(2, address.getNumber());
            stm.setString(3, address.getComplement());
            stm.setString(4, address.getNeighborhood());
            stm.setString(5, address.getCity());
            stm.setString(6, address.getState());
            stm.setString(7, address.getCountry());
            stm.setString(8, address.getCep());
            stm.setString(9, address.getCustomer().getId().toString());
            stm.setString(10, address.getId().toString());
            int rowsAffected = stm.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(UUID id) {
        String sql = "DELETE FROM endereco WHERE id = ?";
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
    public Address findById(UUID id, Customer customer) {

        String sql = "select * from endereco E where E.id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, id.toString());
            ResultSet rs = stm.executeQuery();
            Address address = null;

            while (rs.next()) {
                String addressId = rs.getString("id");
                String publicPlace = rs.getString("logradouro");
                Integer number = rs.getInt("numero");
                String complement = rs.getString("complemento");
                String neighborhood = rs.getString("bairro");
                String city = rs.getString("cidade");
                String state = rs.getString("estado");
                String country = rs.getString("pais");
                String cep = rs.getString("cep");
                address = Address.with(UUID.fromString(addressId), publicPlace, number, complement, neighborhood, state, city, country, cep, customer);
            }
            rs.close();

            return address;
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Address> findAllByCostumer(Customer customer) {
        String sql = "select * from endereco E where E.id_cliente = ?";
        List<Address> addresses = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, customer.getId().toString());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String publicPlace = rs.getString("logradouro");
                Integer number = rs.getInt("numero");
                String complement = rs.getString("complemento");
                String neighborhood = rs.getString("bairro");
                String city = rs.getString("cidade");
                String state = rs.getString("estado");
                String country = rs.getString("pais");
                String cep = rs.getString("cep");
                Address address = Address.with(UUID.fromString(id), publicPlace, number, complement, neighborhood, state, city, country, cep, customer);
                addresses.add(address);
            }
            rs.close();
            return addresses;
        } catch (Exception err) {
            err.printStackTrace();
            return new ArrayList<>();
        }
    }
}
