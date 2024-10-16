package com.pratopronto.prato_pronto_api.domain.credit_card;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;

import java.util.Date;
import java.util.UUID;

public class CreditCard {

    private final UUID id;

    private final String token;

    private final String holder;

    private final String flag;

    private final String expirationDate;

    private final Consumer consumer;

    private CreditCard(UUID id, String token, String holder, String flag, String expirationDate, Consumer consumer) {
        this.id = id;
        this.token = token;
        this.holder = holder;
        this.flag = flag;
        this.expirationDate = expirationDate;
        this.consumer = consumer;
    }

    public UUID getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getHolder() {
        return holder;
    }

    public String getFlag() {
        return flag;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public static CreditCard create(
            String token, String holder, String flag, String expirationDate, Consumer consumer
    ) {
        return new CreditCard(
                UUID.randomUUID(), token, holder, flag, expirationDate, consumer
        );
    }

    public static CreditCard with(
            UUID id, String token, String holder, String flag, String expirationDate, Consumer consumer
    ) {
        return new CreditCard(id, token, holder, flag, expirationDate, consumer);
    }
}
