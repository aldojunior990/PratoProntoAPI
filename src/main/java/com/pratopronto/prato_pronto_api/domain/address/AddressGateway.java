package com.pratopronto.prato_pronto_api.domain.address;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;

import java.util.List;
import java.util.UUID;

public interface AddressGateway {

    void save(Address address);

    Boolean update(Address address);

    Boolean delete(UUID id);

    Address findById(UUID id, Customer customer);

    List<Address> findAllByCostumer(Customer customer);


}
