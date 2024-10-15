package com.pratopronto.prato_pronto_api.domain.contact;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;

import java.util.UUID;

public class Contact {

    private final UUID id;

    private final String number;

    private final String type;

    private final Customer customer;

    private Contact(UUID id, String number, String type, Customer customer) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static Contact create(
            String number, String type, Customer customer
    ) {
        return new Contact(UUID.randomUUID(), number, type, customer);
    }

    public static Contact with(
            UUID id, String number, String type, Customer customer
    ) {
        return new Contact(id, number, type, customer);
    }
}
