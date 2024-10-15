package com.pratopronto.prato_pronto_api.controllers.address;

import com.pratopronto.prato_pronto_api.usecases.address.*;
import com.pratopronto.prato_pronto_api.usecases.address.dtos.AddressDTO;
import com.pratopronto.prato_pronto_api.usecases.address.dtos.FindAddressByIdInputDTO;
import com.pratopronto.prato_pronto_api.usecases.address.dtos.FindAddressInputDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private SaveAddress saveAddress;

    @Autowired
    private FindAddressByEmail findAddressByEmail;

    @Autowired
    private UpdateAddress updateAddress;

    @Autowired
    private FindAddressById findAddressById;

    @Autowired
    private DeleteAddress deleteAddress;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody AddressDTO data, HttpServletRequest request) {
        return saveAddress.execute(new FindAddressInputDTO(data, request));
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody AddressDTO data, HttpServletRequest request) {
        return updateAddress.execute(new FindAddressInputDTO(data, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findByAddressId(@PathVariable String id, HttpServletRequest request) {
        return findAddressById.execute(new FindAddressByIdInputDTO(UUID.fromString(id), request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return deleteAddress.execute(id);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAllByEmail(HttpServletRequest request) {
        return findAddressByEmail.execute(request);
    }

}
