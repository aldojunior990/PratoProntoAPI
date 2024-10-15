package com.pratopronto.prato_pronto_api.controllers.contact;

import com.pratopronto.prato_pronto_api.usecases.contact.*;
import com.pratopronto.prato_pronto_api.usecases.contact.dtos.ContactDTO;
import com.pratopronto.prato_pronto_api.usecases.contact.dtos.HttpRequestInputDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contact")
public class ContactController {

    @Autowired
    private SaveContact saveContact;

    @Autowired
    private UpdateContact updateContact;

    @Autowired
    private DeleteContact deleteContact;

    @Autowired
    private FindContactById findContactById;

    @Autowired
    private FindContactsByCustomer findContactsByCustomer;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ContactDTO contactDTO, HttpServletRequest httpServletRequest) {
        return saveContact.execute(new HttpRequestInputDTO<>(contactDTO, httpServletRequest));
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ContactDTO contactDTO, HttpServletRequest httpServletRequest) {
        return updateContact.execute(new HttpRequestInputDTO<>(contactDTO, httpServletRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return deleteContact.execute(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> findById(@PathVariable String id, HttpServletRequest httpServletRequest) {
        return findContactById.execute(new HttpRequestInputDTO<>(id, httpServletRequest));
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> findByCustomer(HttpServletRequest httpServletRequest) {
        return findContactsByCustomer.execute(httpServletRequest);
    }
}
