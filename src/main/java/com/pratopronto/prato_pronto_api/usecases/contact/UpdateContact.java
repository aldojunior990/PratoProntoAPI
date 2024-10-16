package com.pratopronto.prato_pronto_api.usecases.contact;

import com.pratopronto.prato_pronto_api.domain.contact.ContactGateway;
import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.contact.dtos.ContactDTO;
import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateContact implements UseCaseContract<HttpRequestDTO<ContactDTO>, ResponseEntity<String>> {


    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Autowired
    private ContactGateway contactGateway;

    @Override
    public ResponseEntity<String> execute(HttpRequestDTO<ContactDTO> data) {

        Customer customer = extractTokenAndReturnCustomer.execute(data.request());

        if (customer == null) return ResponseEntity.badRequest().body("Usuario não cadastrado");

        Boolean isUpdated = contactGateway.update(data.content().toWithContact(customer));

        if (!isUpdated) return ResponseEntity.badRequest().body("Não foi possivel atualizar o contato");

        return ResponseEntity.ok("Contato atualizado");
    }
}
