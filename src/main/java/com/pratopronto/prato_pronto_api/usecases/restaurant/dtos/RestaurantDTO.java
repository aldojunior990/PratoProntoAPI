package com.pratopronto.prato_pronto_api.usecases.restaurant.dtos;

import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;

import java.util.UUID;

public record RestaurantDTO(
        UUID id, String name, String cookingType, Double grade) {

    public static RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        return new RestaurantDTO(
                restaurant.getId(), restaurant.getName(), restaurant.getCookingType(), restaurant.getGrade()
        );
    }

    public Restaurant toRestaurant() {
        return Restaurant.with(id, name, cookingType, grade);
    }

}
