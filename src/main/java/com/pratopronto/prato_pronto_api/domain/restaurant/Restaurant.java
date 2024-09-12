package com.pratopronto.prato_pronto_api.domain.restaurant;

import java.util.UUID;

public class Restaurant {
    private final UUID id;

    private final String name;

    private final String cookingType;

    private final Double grade;

    private Restaurant(UUID id, String name, String cookingType, Double grade) {
        this.id = id;
        this.cookingType = cookingType;
        this.name = name;
        this.grade = grade;
    }


    public static Restaurant create(String name, String cookingType) {
        return new Restaurant(UUID.randomUUID(), name, cookingType, 0.0);
    }

    public static Restaurant with(UUID id, String name, String cookingType, Double grade) {
        return new Restaurant(id, name, cookingType, grade);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCookingType() {
        return cookingType;
    }

    public Double getGrade() {
        return grade;
    }
}
