package com.pratopronto.prato_pronto_api.usecases.consumer.getConsumer;

import java.util.UUID;

public record GetConsumerOutput(UUID id, String name, String lastName, String cpf) {
}
