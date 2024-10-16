package com.pratopronto.prato_pronto_api.infra.repositories;

import com.pratopronto.prato_pronto_api.domain.consumer.Consumer;
import com.pratopronto.prato_pronto_api.domain.credit_card.CreditCard;
import com.pratopronto.prato_pronto_api.domain.credit_card.CreditCardGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public class CreditCardRepository implements CreditCardGateway {

    @Autowired
    private Connection connection;

    @Override
    public boolean save(CreditCard creditCard) {
        String sql = "insert into cartao_de_credito(token, titular, bandeira, data_validade, id_consumidor) values (?,?,?,?,?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, creditCard.getToken());
            stm.setString(2, creditCard.getHolder());
            stm.setString(3, creditCard.getFlag());
            stm.setString(4, creditCard.getExpirationDate());
            stm.setString(5, creditCard.getConsumer().getId().toString());

            int rowsAffected = stm.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(CreditCard creditCard) {
        String sql = "update cartao_de_credito set token=?, titular=?, bandeira=?, data_validade=? where id=?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, creditCard.getToken());
            stm.setString(2, creditCard.getHolder());
            stm.setString(3, creditCard.getFlag());
            stm.setString(4, creditCard.getExpirationDate());
            stm.setString(5, creditCard.getId().toString());
            int rowsAffected = stm.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM cartao_de_credito WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, id);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CreditCard> findAllByConsumer(Consumer consumer) {
        String sql = "select * from cartao_de_credito C where C.id_consumidor = ?";
        List<CreditCard> creditCards = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, consumer.getId().toString());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String token = rs.getString("token");
                String holder = rs.getString("titular");
                String flag = rs.getString("bandeira");
                String expirationDate = rs.getString("data_validade");
                CreditCard creditCard = CreditCard.with(UUID.fromString(id), token, holder, flag, expirationDate, consumer);
                creditCards.add(creditCard);
            }
            rs.close();
            return creditCards;
        } catch (Exception err) {
            err.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public CreditCard findById(String id, Consumer consumer) {

        String sql = "select * from cartao_de_credito C where C.id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            CreditCard creditCard = null;
            while (rs.next()) {
                String creditCardId = rs.getString("id");
                String token = rs.getString("token");
                String holder = rs.getString("titular");
                String flag = rs.getString("bandeira");
                String expirationDate = rs.getString("data_validade");
                creditCard = CreditCard.with(UUID.fromString(creditCardId), token, holder, flag, expirationDate, consumer);

            }
            rs.close();
            return creditCard;
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }
}
