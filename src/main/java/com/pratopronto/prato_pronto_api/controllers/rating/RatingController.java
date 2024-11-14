package com.pratopronto.prato_pronto_api.controllers.rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratopronto.prato_pronto_api.usecases.rating.FindRatingsByRestaurant;
import com.pratopronto.prato_pronto_api.usecases.rating.SaveRating;
import com.pratopronto.prato_pronto_api.usecases.rating.dtos.RatingDTO;

@RestController
@RequestMapping("rating")

public class RatingController {
    @Autowired 
    private SaveRating saveRating;
    @Autowired 
    private FindRatingsByRestaurant findRatingsByRestaurant;

    @PostMapping
    public ResponseEntity<String>Save(@RequestBody RatingDTO ratingDTO){
        return saveRating.execute(ratingDTO);
    }
    
    @GetMapping("/{id}") 
    public ResponseEntity<List<RatingDTO>>Get(@PathVariable String id){
        return findRatingsByRestaurant.execute(id);
    }
}
