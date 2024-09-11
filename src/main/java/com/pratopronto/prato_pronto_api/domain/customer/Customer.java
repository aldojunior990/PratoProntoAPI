package com.pratopronto.prato_pronto_api.domain.customer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

public class Customer implements UserDetails {

    private final UUID id;

    private final String email;

    private final String password;

    private Customer(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static Customer create(String email, String password) {
        return new Customer(UUID.randomUUID(), email, password);
    }

    public static Customer with(UUID id, String email, String password) {
        return new Customer(id, email, password);
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
