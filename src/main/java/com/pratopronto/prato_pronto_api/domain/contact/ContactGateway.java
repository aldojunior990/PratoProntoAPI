package com.pratopronto.prato_pronto_api.domain.contact;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;

import java.util.List;
import java.util.UUID;

public interface ContactGateway {
    Boolean save(Contact contact);

    Boolean update(Contact contact);

    Boolean delete(UUID id);

    List<Contact> findAllByCustomer(Customer customer);

    Contact findById(UUID id, Customer customer);
}
