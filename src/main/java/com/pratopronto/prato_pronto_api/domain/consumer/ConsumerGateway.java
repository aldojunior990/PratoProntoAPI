package com.pratopronto.prato_pronto_api.domain.consumer;

import java.sql.SQLException;
import java.util.UUID;

public interface ConsumerGateway {

    void save(Consumer consumer);

    void delete(UUID id);

    void update(Consumer consumer);

    Consumer findByEmail(String email);
}
