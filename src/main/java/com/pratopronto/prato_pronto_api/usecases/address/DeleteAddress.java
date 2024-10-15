package com.pratopronto.prato_pronto_api.usecases.address;

import com.pratopronto.prato_pronto_api.domain.address.AddressGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class DeleteAddress implements UseCaseContract<String, ResponseEntity<String>> {
    @Autowired
    private AddressGateway addressGateway;

    @Override
    public ResponseEntity<String> execute(String data) {
        try {
            Boolean isDeleted = addressGateway.delete(UUID.fromString(data));

            if (!isDeleted) return ResponseEntity.badRequest().body("Não foi possivel deletar");
            return ResponseEntity.ok("Endereço deletado com sucesso");
        } catch (Exception err) {
            return ResponseEntity.internalServerError().body(err.getMessage());
        }
    }
}
