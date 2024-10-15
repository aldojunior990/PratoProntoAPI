package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.address.Address;
import com.pratopronto.prato_pronto_api.domain.contact.Contact;
import com.pratopronto.prato_pronto_api.domain.contact.ContactGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ContactRepository implements ContactGateway {

    @Autowired
    private Connection connection;

    @Override
    public Boolean save(Contact contact) {
        String sql = "insert into contato(id, numero, tipo, id_cliente) values(?,?,?,?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, contact.getId().toString());
            stm.setString(2, contact.getNumber());
            stm.setString(3, contact.getType());
            stm.setString(4, contact.getCustomer().getId().toString());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(Contact contact) {
        String sql = "update contato set numero=?, tipo=? where id=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, contact.getNumber());
            stm.setString(2, contact.getType());
            stm.setString(3, contact.getId().toString());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(UUID id) {
        String sql = "DELETE FROM contato WHERE id = ?";
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
    public List<Contact> findAllByCustomer(Customer customer) {
        String sql = "select * from contato C where C.id_cliente = ?";
        List<Contact> contacts = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, customer.getId().toString());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String number = rs.getString("numero");
                String type = rs.getString("tipo");
                Contact contact = Contact.with(UUID.fromString(id), number, type, customer);
                contacts.add(contact);
            }
            rs.close();
            return contacts;
        } catch (Exception err) {
            err.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Contact findById(UUID id, Customer customer) {
        String sql = "select * from contato E where E.id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, id.toString());
            ResultSet rs = stm.executeQuery();
            Contact contact = null;

            while (rs.next()) {
                String contactId = rs.getString("id");
                String number = rs.getString("numero");
                String type = rs.getString("tipo");
                contact = Contact.with(UUID.fromString(contactId), number, type, customer);
            }
            rs.close();
            return contact;
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }
}
