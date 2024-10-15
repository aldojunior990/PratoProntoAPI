package com.pratopronto.prato_pronto_api.usecases.contact;

import com.pratopronto.prato_pronto_api.domain.contact.Contact;
import com.pratopronto.prato_pronto_api.domain.contact.ContactGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.contact.dtos.ContactDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindContactsByCustomer implements UseCaseContract<HttpServletRequest, ResponseEntity<List<ContactDTO>>> {

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private ContactGateway contactGateway;

    @Override
    public ResponseEntity<List<ContactDTO>> execute(HttpServletRequest data) {

        Customer customer = extractTokenAndReturnCustomer.execute(data);

        ArrayList<ContactDTO> response = new ArrayList<>();

        if (customer == null) return ResponseEntity.badRequest().body(null);

        List<Contact> listOfContacts = contactGateway.findAllByCustomer(customer);

        for (Contact it : listOfContacts) {
            response.add(ContactDTO.toContactDTO(it));
        }

        return ResponseEntity.ok(response);
    }
}
