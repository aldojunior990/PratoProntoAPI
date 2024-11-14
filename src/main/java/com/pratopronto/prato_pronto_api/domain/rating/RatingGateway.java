package com.pratopronto.prato_pronto_api.domain.rating;


import java.util.List;


public interface RatingGateway {

    boolean save(Rating rating);

    List<Rating> findAllByRestaurant(String restaurantID);

}
