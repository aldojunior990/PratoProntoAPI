package com.pratopronto.prato_pronto_api.usecases.rating;

import com.pratopronto.prato_pronto_api.domain.rating.Rating;
import com.pratopronto.prato_pronto_api.domain.rating.RatingGateway;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import com.pratopronto.prato_pronto_api.domain.restaurant.RestaurantGateway;
import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.consumer.ConsumerGateway;
import com.pratopronto.prato_pronto_api.usecases.rating.dtos.RatingDTO;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaveRating implements UseCaseContract<RatingDTO, ResponseEntity<String>> {

    @Autowired
    private RatingGateway ratingGateway;


    @Override
    public ResponseEntity<String> execute(RatingDTO ratingDTO) {
        try {

            boolean isSaved = ratingGateway.save(Rating.with(UUID.randomUUID(), ratingDTO.feedback(), ratingDTO.nota(), ratingDTO.restaurantId(), ratingDTO.consumerId()));

            if (!isSaved) return ResponseEntity.badRequest().body("Não foi possível salvar a avaliação");

            return ResponseEntity.ok("Avaliação salva com sucesso");
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
