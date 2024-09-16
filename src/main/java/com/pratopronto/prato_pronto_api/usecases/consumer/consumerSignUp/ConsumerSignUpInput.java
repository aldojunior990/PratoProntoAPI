package com.pratopronto.prato_pronto_api.usecases.consumer.consumerSignUp;

public record ConsumerSignUpInput(String email, String password, String name, String lastName, String cpf) {
}
