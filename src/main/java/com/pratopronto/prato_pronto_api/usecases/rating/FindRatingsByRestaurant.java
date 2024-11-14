package com.pratopronto.prato_pronto_api.usecases.rating;

import com.pratopronto.prato_pronto_api.domain.rating.Rating;
import com.pratopronto.prato_pronto_api.domain.rating.RatingGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.usecases.rating.dtos.RatingDTO;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FindRatingsByRestaurant implements UseCaseContract<String, ResponseEntity<List<RatingDTO>>> {

    @Autowired
    private RatingGateway ratingGateway;

    @Override
    public ResponseEntity<List<RatingDTO>> execute(String restaurantID) {
        try {
            List<Rating>ratings = ratingGateway.findAllByRestaurant(restaurantID); 
            ArrayList<RatingDTO>ratingsDTO = new ArrayList<>();
            for(Rating it: ratings){
                ratingsDTO.add(new RatingDTO(it.getId(), it.getFeedback(), it.getNota(), it.getRestaurantID(), it.getConsumerID()));
            }
            return ResponseEntity.ok(ratingsDTO);
    
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
