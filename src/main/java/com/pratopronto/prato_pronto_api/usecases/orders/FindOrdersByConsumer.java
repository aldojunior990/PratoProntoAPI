package com.pratopronto.prato_pronto_api.usecases.orders;

import com.pratopronto.prato_pronto_api.domain.order.Order;
import com.pratopronto.prato_pronto_api.domain.order.OrderGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.orders.dtos.FindOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindOrdersByConsumer implements UseCaseContract<String, ResponseEntity<List<FindOrderDTO>>> {

    @Autowired
    private OrderGateway orderGateway;

    @Override
    public ResponseEntity<List<FindOrderDTO>> execute(String id) {
        try {
            ArrayList<FindOrderDTO> orderDTOS = new ArrayList<>();
            List<Order> orders = orderGateway.findAllByConsumerID(id);

            for (Order it : orders) {
                orderDTOS.add(new FindOrderDTO(it.getId().toString(), it.getDateTime(), it.getStatus(), it.getTotalPrice(), it.getConsumerID(), it.getRestaurantID(), ""));
            }

            return ResponseEntity.ok(orderDTOS);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
