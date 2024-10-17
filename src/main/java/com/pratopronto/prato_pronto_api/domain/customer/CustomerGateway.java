package com.pratopronto.prato_pronto_api.domain.customer;

import org.springframework.security.core.userdetails.UserDetails;

import java.sql.SQLException;

public interface CustomerGateway {

    void save(Customer customer);

    boolean delete(String id);

    UserDetails findByEmail(String email);


}
