package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.contact.Contact;
import com.pratopronto.prato_pronto_api.domain.products.Product;
import com.pratopronto.prato_pronto_api.domain.products.ProductGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements ProductGateway {

    @Autowired
    private Connection connection;

    @Override
    public boolean save(Product product) {
        String sql = "insert into produto(id, nome, descricao, preco, stats, id_restaurante) values(?,?,?,?,?,?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, product.getId().toString());
            stm.setString(2, product.getName());
            stm.setString(3, product.getDescription());
            stm.setDouble(4, product.getPrice());
            stm.setString(5, product.getState());
            stm.setString(6, product.getRestaurant().getId().toString());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        String sql = "update produto set nome=?, descricao=?, preco=?, stats=? where id=? and id_restaurante=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, product.getName());
            stm.setString(2, product.getDescription());
            stm.setDouble(3, product.getPrice());
            stm.setString(4, product.getState());
            stm.setString(5, product.getId().toString());
            stm.setString(6, product.getRestaurant().getId().toString());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        String sql = "DELETE FROM produto WHERE id = ? and id_restaurante=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, product.getId().toString());
            stm.setString(2, product.getRestaurant().toString());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> findAllByRestaurant(Restaurant restaurant) {
        String sql = "select * from produto P where P.id_restaurante = ?";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, restaurant.getId().toString());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("nome");
                String description = rs.getString("descricao");
                Double price = rs.getDouble("preco");
                String stats = rs.getString("stats");
                Product product = Product.with(UUID.fromString(id), name, description, price, stats, restaurant);
                products.add(product);
            }
            rs.close();
            return products;
        } catch (Exception err) {
            err.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Product findById(String productId) {
        String sql = "select * from produto P where P.id = ?";

        Product product = null;

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, productId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("nome");
                String description = rs.getString("descricao");
                Double price = rs.getDouble("preco");
                String stats = rs.getString("stats");
                product = Product.with(UUID.fromString(id), name, description, price, stats, null);
            }
            rs.close();
            return product;
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }
}
