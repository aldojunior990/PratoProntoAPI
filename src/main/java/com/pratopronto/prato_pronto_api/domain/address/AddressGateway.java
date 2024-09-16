package com.pratopronto.prato_pronto_api.domain.address;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;

import java.util.List;
import java.util.UUID;

public interface AddressGateway {

    void save(Address address);

    void update(Address address);

    void delete(UUID id);

    Address findById();

    List<Address> findAllByCostumer(Customer customer);


}
