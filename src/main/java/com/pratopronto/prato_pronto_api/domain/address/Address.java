package com.pratopronto.prato_pronto_api.domain.address;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {

    private final UUID id;

    private final String publicPlace;

    private final Integer number;

    private final String complement;

    private final String neighborhood;

    private final String state;

    private final String city;
    private final String country;

    private final String cep;

    private final Customer customer;

    public UUID getId() {
        return id;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public Integer getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCep() {
        return cep;
    }

    public String getCity() {
        return city;
    }

    public Customer getCustomer() {
        return customer;
    }

    private Address(UUID id, String publicPlace, Integer number, String complement, String neighborhood, String state, String city, String country, String cep, Customer customer) throws Exception {


        this.validateCEP(cep);

        this.id = id;
        this.publicPlace = publicPlace;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.state = state;
        this.country = country;
        this.cep = cep;
        this.city = city;
        this.customer = customer;
    }

    public static Address create(
            String publicPlace, Integer number, String complement, String neighborhood, String city, String state, String country, String cep, Customer customer
    ) throws Exception {

        return new Address(
                UUID.randomUUID(),
                publicPlace,
                number,
                complement,
                neighborhood,
                state, city,
                country,
                cep, customer);
    }


    public static Address with(
            UUID id, String publicPlace, Integer number, String complement, String neighborhood, String city, String state, String country, String cep, Customer customer
    ) throws Exception {
        return new Address(
                id,
                publicPlace,
                number,
                complement,
                neighborhood,
                state, city,
                country,
                cep, customer);
    }

    private void validateCEP(String cep) throws Exception {
        String regex = "^[0-9]{5}-?[0-9]{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cep);
        if (!matcher.matches()) throw new Exception("CEP inválido");
    }
}
