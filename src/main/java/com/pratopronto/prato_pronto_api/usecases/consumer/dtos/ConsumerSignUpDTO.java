package com.pratopronto.prato_pronto_api.usecases.consumer.dtos;

public record ConsumerSignUpDTO(String email, String password, String name, String lastName, String cpf) {
}
