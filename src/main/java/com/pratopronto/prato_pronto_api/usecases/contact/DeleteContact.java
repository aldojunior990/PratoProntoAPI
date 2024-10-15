package com.pratopronto.prato_pronto_api.usecases.contact;

import com.pratopronto.prato_pronto_api.domain.contact.ContactGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class DeleteContact implements UseCaseContract<String, ResponseEntity<String>> {


    @Autowired
    private ContactGateway contactGateway;

    @Override
    public ResponseEntity<String> execute(String id) {
        try {
            Boolean isDeleted = contactGateway.delete(UUID.fromString(id));
            if (!isDeleted) return ResponseEntity.badRequest().body("Não foi possivel deletar");
            return ResponseEntity.ok("Contato deletado com sucesso");
        } catch (Exception err) {
            return ResponseEntity.internalServerError().body(err.getMessage());
        }
    }
}
