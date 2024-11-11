package com.pratopronto.prato_pronto_api.domain.customer;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.restaurant.Restaurant;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.SQLException;
import java.util.UUID;

public interface CustomerGateway {

    void save(Customer customer);

    boolean delete(String id);

    UserDetails findByEmail(String email);

    boolean saveNewConsumer(Customer customer, Consumer consumer);

    boolean saveNewRestaurant(Customer customer, Restaurant restaurant);


    CustomerDetails findCustomerDetails(UUID id);


}
