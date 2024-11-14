package com.pratopronto.prato_pronto_api.domain.rating;


import java.util.UUID;

public class Rating {

    private final UUID id;
    private final String feedback;
    private final Double nota;
    private final String restaurantID;
    private final String consumerID;

    public UUID getId() {
        return id;
    }

    public String getFeedback() {
        return feedback;
    }


    public Double getNota() {
        return nota;
    }


    public String getRestaurantID() {
        return restaurantID;
    }

    public String getConsumerID() {
        return consumerID;
    }

    private Rating(UUID id, String feedback, Double nota, String restaurantID, String consumerID) {
        this.id = id;
        this.feedback = feedback;
        this.nota = nota;
        this.restaurantID = restaurantID;
        this.consumerID = consumerID;
    }

    public static Rating create(
            String feedback, String description, Double nota, String state, String restaurantID, String consumerID
    ) {
        return new Rating(UUID.randomUUID(), feedback, nota, restaurantID, consumerID);
    }

    public static Rating with(
            UUID id, String feedback, Double nota, String restaurantID, String consumerID
    ) {
        return new Rating(id, feedback, nota, restaurantID, consumerID);
    }
}
