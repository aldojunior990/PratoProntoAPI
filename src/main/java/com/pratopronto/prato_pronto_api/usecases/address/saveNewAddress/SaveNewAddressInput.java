package com.pratopronto.prato_pronto_api.usecases.address.saveNewAddress;

import com.pratopronto.prato_pronto_api.usecases.address.AddressDTO;
import jakarta.servlet.http.HttpServletRequest;

public record SaveNewAddressInput(
        AddressDTO address, HttpServletRequest request
) {
}
