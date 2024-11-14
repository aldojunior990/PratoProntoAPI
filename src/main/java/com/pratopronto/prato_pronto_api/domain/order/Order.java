package com.pratopronto.prato_pronto_api.domain.order;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID id;

    private final Date dateTime;

    private final String status;

    private final String paymentMethod;

    private final Double totalPrice;

    private final String consumerID;

    private final String restaurantID;


    private final List<OrderItem> items;

    public Order(UUID id, Date dateTime, String status, String paymentMethod, Double totalPrice, String consumerID, String restaurantID, List<OrderItem> items) {
        this.dateTime = dateTime;
        this.id = id;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.consumerID = consumerID;
        this.restaurantID = restaurantID;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getConsumerID() {
        return consumerID;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Date getDateTime() {
        return dateTime;
    }
}
