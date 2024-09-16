package com.pratopronto.prato_pronto_api.controllers.address;

import com.pratopronto.prato_pronto_api.usecases.address.AddressDTO;
import com.pratopronto.prato_pronto_api.usecases.address.findAddresByEmail.FindAddressByEmailUseCase;
import com.pratopronto.prato_pronto_api.usecases.address.saveNewAddress.SaveNewAddressInput;
import com.pratopronto.prato_pronto_api.usecases.address.saveNewAddress.SaveNewAddressOutput;
import com.pratopronto.prato_pronto_api.usecases.address.saveNewAddress.SaveNewAddressUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private SaveNewAddressUseCase saveNewAddressUseCase;

    @Autowired
    private FindAddressByEmailUseCase findAddressByEmailUseCase;

    @PostMapping
    public ResponseEntity<SaveNewAddressOutput> saveNewAddress(@RequestBody AddressDTO data, HttpServletRequest request) {
        return saveNewAddressUseCase.execute(new SaveNewAddressInput(data, request));
    }


    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAllByEmail(HttpServletRequest request) {
        return findAddressByEmailUseCase.execute(request);
    }

}
