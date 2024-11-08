package com.pratopronto.prato_pronto_api.controllers.credit_card;

import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.credit_card.*;
import com.pratopronto.prato_pronto_api.usecases.credit_card.dtos.CreditCardDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("credit-card")
public class CreditCardController {
    @Autowired
    private SaveCreditCard saveCreditCard;

    @Autowired
    private UpdateCreditCard updateCreditCard;

    @Autowired
    private FindAllCreditCardByConsumer findAllCreditCardByConsumer;

    @Autowired
    private FindCreditCardById findCreditCardById;

    @Autowired
    private DeleteCreditCard deleteCreditCard;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CreditCardDTO data, HttpServletRequest httpServletRequest) {
        return saveCreditCard.execute(new HttpRequestDTO<>(data, httpServletRequest));
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody CreditCardDTO data, HttpServletRequest httpServletRequest) {
        return updateCreditCard.execute(new HttpRequestDTO<>(data, httpServletRequest));
    }

    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> findAll(HttpServletRequest httpServletRequest) {
        return findAllCreditCardByConsumer.execute(httpServletRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardDTO> findById(@PathVariable String id, HttpServletRequest httpServletRequest) {
        return findCreditCardById.execute(new HttpRequestDTO<>(id, httpServletRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return deleteCreditCard.execute(id);
    }


}
