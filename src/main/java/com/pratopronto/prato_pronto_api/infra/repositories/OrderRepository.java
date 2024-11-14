package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.order.Order;
import com.pratopronto.prato_pronto_api.domain.order.OrderGateway;
import com.pratopronto.prato_pronto_api.domain.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository implements OrderGateway {

    @Autowired
    private Connection connection;

    public boolean save(Order order) {
        String orderSQL = "INSERT INTO pedidos (id, data_hora, stats, forma_pagamento, valor_total, id_consumidor, id_restaurante) VALUES (?,?,?,?,?,?,?)";
        String orderItemSQL = "INSERT INTO item_pedido (id_pedido, id_produto, qtd, observacoes) VALUES (?,?,?,?)";
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement orderStmt = connection.prepareStatement(orderSQL)) {
                orderStmt.setString(1, order.getId().toString());
                orderStmt.setDate(2, new java.sql.Date(order.getDateTime().getTime()));
                orderStmt.setString(3, order.getStatus());
                orderStmt.setString(4, order.getPaymentMethod());
                orderStmt.setDouble(5, order.getTotalPrice());
                orderStmt.setString(6, order.getConsumerID());
                orderStmt.setString(7, order.getRestaurantID());

                orderStmt.executeUpdate();
            }
            try (PreparedStatement itemStmt = connection.prepareStatement(orderItemSQL)) {
                for (OrderItem item : order.getItems()) {
                    itemStmt.setString(1, order.getId().toString());
                    itemStmt.setString(2, item.productID());
                    itemStmt.setInt(3, item.amount());
                    itemStmt.setString(4, item.observations());
                    itemStmt.executeUpdate();
                }
            }
            connection.commit();
            return true;

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateStatus(String orderID, String status) {
        String updateOrderSQL = "UPDATE pedidos SET stats = ? WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(updateOrderSQL)) {
            stm.setString(1, status);
            stm.setString(2, orderID);

            System.out.println(orderID + status);

            int rowsAffected = stm.executeUpdate();

            System.out.println(rowsAffected);

            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Order> findAllByConsumerID(String id) {
        String findOrdersSQL = "SELECT * FROM pedidos WHERE id_consumidor = ?";
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(findOrdersSQL)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                UUID orderId = UUID.fromString(rs.getString("id"));
                Date dateTime = rs.getTimestamp("data_hora");
                String status = rs.getString("stats");
                String paymentMethod = rs.getString("forma_pagamento");
                double totalPrice = rs.getDouble("valor_total");
                String restaurantID = rs.getString("id_restaurante");


                String findItemsSQL = "SELECT * FROM item_pedido WHERE id_pedido = ?";
                List<OrderItem> items = new ArrayList<>();
                try (PreparedStatement itemStmt = connection.prepareStatement(findItemsSQL)) {
                    itemStmt.setString(1, orderId.toString());
                    ResultSet itemRs = itemStmt.executeQuery();

                    while (itemRs.next()) {
                        String productID = itemRs.getString("id_produto");
                        int amount = itemRs.getInt("qtd");
                        double subtotal = itemRs.getDouble("subtotal");
                        String observations = itemRs.getString("observacoes");
                        OrderItem item = new OrderItem(orderId.toString(), productID, observations, amount, subtotal);
                        items.add(item);
                    }
                }

                Order order = new Order(orderId, dateTime, status, paymentMethod, totalPrice, id, restaurantID, items);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> findAllByRestaurantID(String id) {
        String findOrdersSQL = "SELECT * FROM pedidos WHERE id_restaurante = ?";
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(findOrdersSQL)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                UUID orderId = UUID.fromString(rs.getString("id"));
                Date dateTime = rs.getTimestamp("data_hora");
                String status = rs.getString("stats");
                String paymentMethod = rs.getString("forma_pagamento");
                double totalPrice = rs.getDouble("valor_total");
                String restaurantID = rs.getString("id_restaurante");


                String findItemsSQL = "SELECT * FROM item_pedido WHERE id_pedido = ?";
                List<OrderItem> items = new ArrayList<>();
                try (PreparedStatement itemStmt = connection.prepareStatement(findItemsSQL)) {
                    itemStmt.setString(1, orderId.toString());
                    ResultSet itemRs = itemStmt.executeQuery();

                    while (itemRs.next()) {
                        String productID = itemRs.getString("id_produto");
                        int amount = itemRs.getInt("qtd");
                        double subtotal = itemRs.getDouble("subtotal");
                        String observations = itemRs.getString("observacoes");
                        OrderItem item = new OrderItem(orderId.toString(), productID, observations, amount, subtotal);
                        items.add(item);
                    }
                }

                Order order = new Order(orderId,dateTime, status, paymentMethod, totalPrice, id, restaurantID, items);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public Order findById(String id) {
        String findOrderSQL = "SELECT * FROM pedidos WHERE id = ?";
        Order order = null;

        try (PreparedStatement stm = connection.prepareStatement(findOrderSQL)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                UUID orderId = UUID.fromString(rs.getString("id"));
                Date dateTime = rs.getTimestamp("data_hora");
                String status = rs.getString("stats");
                String paymentMethod = rs.getString("forma_pagamento");
                double totalPrice = rs.getDouble("valor_total");
                String consumerID = rs.getString("id_consumidor");
                String restaurantID = rs.getString("id_restaurante");


                String findItemsSQL = "SELECT * FROM item_pedido WHERE id_pedido = ?";
                List<OrderItem> items = new ArrayList<>();
                try (PreparedStatement itemStmt = connection.prepareStatement(findItemsSQL)) {
                    itemStmt.setString(1, orderId.toString());
                    ResultSet itemRs = itemStmt.executeQuery();

                    while (itemRs.next()) {
                        String productID = itemRs.getString("id_produto");
                        int amount = itemRs.getInt("qtd");
                        double subtotal = itemRs.getDouble("subtotal");
                        String observations = itemRs.getString("observacoes");
                        OrderItem item = new OrderItem(orderId.toString(), productID, observations, amount, subtotal);
                        items.add(item);
                    }
                }

                order = new Order(orderId, dateTime, status, paymentMethod, totalPrice, consumerID, restaurantID, items);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }
}
