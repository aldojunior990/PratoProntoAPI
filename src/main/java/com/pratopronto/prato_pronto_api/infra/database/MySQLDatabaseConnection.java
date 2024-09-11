package com.pratopronto.prato_pronto_api.infra.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class MySQLDatabaseConnection {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException err) {
            err.printStackTrace();
        }

        return conn;
    }
}
