package com.pratopronto.prato_pronto_api.usecases.orders;

import com.pratopronto.prato_pronto_api.domain.order.OrderGateway;
import com.pratopronto.prato_pronto_api.usecases.UseCaseContract;
import com.pratopronto.prato_pronto_api.usecases.orders.dtos.UpdateOderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderStatus implements UseCaseContract<UpdateOderDTO, ResponseEntity<String>> {

    @Autowired
    private OrderGateway orderGateway;

    @Override
    public ResponseEntity<String> execute(UpdateOderDTO updateOderDTO) {
        try {
            boolean isUpdated = orderGateway.updateStatus(updateOderDTO.orderID(), updateOderDTO.status());

            if (isUpdated) return ResponseEntity.ok().build();

            return ResponseEntity.badRequest().build();

        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
