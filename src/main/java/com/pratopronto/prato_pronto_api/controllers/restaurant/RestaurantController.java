package com.pratopronto.prato_pronto_api.controllers.restaurant;

import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.customer.DeleteCustomer;
import com.pratopronto.prato_pronto_api.usecases.restaurant.FindAllRestaurants;
import com.pratopronto.prato_pronto_api.usecases.restaurant.FindRestaurantById;
import com.pratopronto.prato_pronto_api.usecases.restaurant.UpdateRestaurant;
import com.pratopronto.prato_pronto_api.usecases.restaurant.dtos.RestaurantDTO;
import com.pratopronto.prato_pronto_api.usecases.restaurant.dtos.UpdateRestaurantDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    @Autowired
    private UpdateRestaurant updateRestaurant;

    @Autowired
    private FindAllRestaurants findAllRestaurants;

    @Autowired
    private FindRestaurantById findRestaurantById;

    @Autowired
    private DeleteCustomer deleteCustomer;

    @PutMapping
    public ResponseEntity<String> update(@RequestBody UpdateRestaurantDTO data, HttpServletRequest httpServletRequest) {
        return updateRestaurant.execute(new HttpRequestDTO<>(data, httpServletRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<RestaurantDTO>> findAll() {
        return findAllRestaurants.execute(0);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> findById(@PathVariable String id) {
        return findRestaurantById.execute(id);
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(HttpServletRequest httpServletRequest) {
        return deleteCustomer.execute(httpServletRequest);
    }
}
