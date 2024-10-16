package com.pratopronto.prato_pronto_api.usecases.contact;

import com.pratopronto.prato_pronto_api.domain.contact.Contact;
import com.pratopronto.prato_pronto_api.domain.contact.ContactGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.contact.dtos.ContactDTO;
import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindContactById implements UseCaseContract<HttpRequestDTO<String>, ResponseEntity<ContactDTO>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private ContactGateway contactGateway;

    @Override
    public ResponseEntity<ContactDTO> execute(HttpRequestDTO<String> data) {
        try {
            Customer customer = extractTokenAndReturnCustomer.execute(data.request());

            if (customer == null) return ResponseEntity.badRequest().body(null);

            Contact contact = contactGateway.findById(UUID.fromString(data.content()), customer);

            return ResponseEntity.ok(ContactDTO.toContactDTO(contact));

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
