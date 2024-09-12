package com.pratopronto.prato_pronto_api.domain.consumer;

import java.sql.SQLException;
import java.util.UUID;

public interface ConsumerGateway {

    void save(Consumer consumer) throws SQLException;

    void delete(UUID id) throws SQLException;

    void update(Consumer consumer) throws SQLException;

    Consumer findByEmail(String email) throws SQLException;
}
