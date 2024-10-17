package com.pratopronto.prato_pronto_api.domain.consumer;

public interface ConsumerGateway {
    boolean save(Consumer consumer);

    boolean update(Consumer consumer);

    Consumer findByEmail(String email);
}
