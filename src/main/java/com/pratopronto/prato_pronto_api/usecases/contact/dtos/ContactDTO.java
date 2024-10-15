package com.pratopronto.prato_pronto_api.usecases.contact.dtos;

import com.pratopronto.prato_pronto_api.domain.contact.Contact;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;

import java.util.UUID;

public record ContactDTO(
        UUID id,
        String number,
        String type
) {
    public static ContactDTO toContactDTO(Contact contact) {
        return new ContactDTO(
                contact.getId(), contact.getNumber(), contact.getType()
        );
    }

    public Contact toCreateContact(Customer customer) {
        return Contact.create(number, type, customer);
    }

    public Contact toWithContact(Customer customer) {
        return Contact.with(id, number, type, customer);
    }
}
