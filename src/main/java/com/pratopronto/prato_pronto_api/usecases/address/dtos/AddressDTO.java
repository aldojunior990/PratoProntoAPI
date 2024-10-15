package com.pratopronto.prato_pronto_api.usecases.address.dtos;

import com.pratopronto.prato_pronto_api.domain.address.Address;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;

import java.util.UUID;

public record AddressDTO(
        UUID id,
        String publicPlace,
        Integer number,
        String complement,
        String neighborhood,
        String state,
        String city,
        String country,
        String cep
) {
    public Address toAddress(Customer customer) throws Exception {
        return Address.with(
                id, publicPlace, number, complement, neighborhood, state, city, country, cep, customer
        );
    }

    public static AddressDTO toAddressDto(Address address) {

        return new AddressDTO(
                address.getId(),
                address.getPublicPlace(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getState(),
                address.getCity(),
                address.getCountry(),
                address.getCep());
    }

}
