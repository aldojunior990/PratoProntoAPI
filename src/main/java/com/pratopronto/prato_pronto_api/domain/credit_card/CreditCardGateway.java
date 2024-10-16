package com.pratopronto.prato_pronto_api.domain.credit_card;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;

import java.util.List;

public interface CreditCardGateway {
    boolean save(CreditCard creditCard);

    boolean update(CreditCard creditCard);

    boolean delete(String id);

    List<CreditCard> findAllByConsumer(Consumer consumer);

    CreditCard findById(String id, Consumer consumer);
}
