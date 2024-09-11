package com.pratopronto.prato_pronto_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class PratoProntoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PratoProntoApiApplication.class, args);
    }


}
