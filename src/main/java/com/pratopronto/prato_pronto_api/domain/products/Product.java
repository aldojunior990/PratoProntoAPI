package com.pratopronto.prato_pronto_api.domain.products;

import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;

import java.util.UUID;

public class Product {

    private final UUID id;

    private final String name;

    private final String description;

    private final Double price;

    private final String state;

    private final Restaurant restaurant;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getState() {
        return state;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    private Product(UUID id, String name, String description, Double price, String state, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.state = state;
        this.restaurant = restaurant;
    }

    public static Product create(
            String name, String description, Double price, String state, Restaurant restaurant
    ) {
        return new Product(UUID.randomUUID(), name, description, price, state, restaurant);
    }

    public static Product with(
            UUID id, String name, String description, Double price, String state, Restaurant restaurant
    ) {
        return new Product(id, name, description, price, state, restaurant);
    }
}
