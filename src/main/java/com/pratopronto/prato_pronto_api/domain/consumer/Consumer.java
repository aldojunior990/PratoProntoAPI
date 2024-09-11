package com.pratopronto.prato_pronto_api.domain.consumer;

import java.util.UUID;

public class Consumer {

    private final UUID id;

    private final String name;

    private final String lastName;
    private final String cpf;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }


    private Consumer(UUID id, String name, String lastName, String cpf) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public static Consumer create(String name, String lastName, String cpf) {
        return new Consumer(
                UUID.randomUUID(),
                name, lastName, cpf
        );
    }

    public static Consumer with(UUID id, String name, String lastName, String cpf) {
        return new Consumer(id, name, lastName, cpf);
    }
}
