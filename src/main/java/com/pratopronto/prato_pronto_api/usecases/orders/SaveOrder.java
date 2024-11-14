package com.pratopronto.prato_pronto_api.usecases.orders;

import com.pratopronto.prato_pronto_api.domain.customer.Customer;
import com.pratopronto.prato_pronto_api.domain.order.Order;
import com.pratopronto.prato_pronto_api.domain.order.OrderGateway;
import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.orders.dtos.SaveOrderDTO;
import com.pratopronto.prato_pronto_api.utils.ExtractTokenAndReturnCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class SaveOrder implements UseCaseContract<HttpRequestDTO<SaveOrderDTO>, ResponseEntity<String>> {

    @Autowired
    private OrderGateway orderGateway;

    @Autowired
    private ExtractTokenAndReturnCustomer extractTokenAndReturnCustomer;

    @Override
    public ResponseEntity<String> execute(HttpRequestDTO<SaveOrderDTO> data) {
        try {

            Customer customer = extractTokenAndReturnCustomer.execute(data.request());

            if (customer != null) {

                Order order = new Order(
                        UUID.randomUUID(),
                        new Date(),
                        "preparacao", data.content().paymentMethod(),
                        0.0, customer.getId().toString(),
                        data.content().restaurantID(), data.content().items());

                boolean isSaved = orderGateway.save(order);

                if (isSaved) return ResponseEntity.ok().build();

                return ResponseEntity.badRequest().build();

            }
            return ResponseEntity.badRequest().build();
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
}
