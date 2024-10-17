package com.pratopronto.prato_pronto_api.usecases.consumer.dtos;

import java.util.UUID;

public record ConsumerDTO(UUID id, String name, String lastName, String cpf) {
}
