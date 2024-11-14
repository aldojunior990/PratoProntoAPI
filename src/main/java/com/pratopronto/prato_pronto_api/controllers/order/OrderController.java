package com.pratopronto.prato_pronto_api.controllers.order;

import com.pratopronto.prato_pronto_api.usecases.HttpRequestDTO;
import com.pratopronto.prato_pronto_api.usecases.orders.FindOrdersByConsumer;
import com.pratopronto.prato_pronto_api.usecases.orders.FindOrdersByRestaurant;
import com.pratopronto.prato_pronto_api.usecases.orders.SaveOrder;
import com.pratopronto.prato_pronto_api.usecases.orders.UpdateOrderStatus;
import com.pratopronto.prato_pronto_api.usecases.orders.dtos.FindOrderDTO;
import com.pratopronto.prato_pronto_api.usecases.orders.dtos.SaveOrderDTO;
import com.pratopronto.prato_pronto_api.usecases.orders.dtos.UpdateOderDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private SaveOrder saveOrder;

    @Autowired
    private FindOrdersByConsumer findOrdersByConsumer;

    @Autowired
    private FindOrdersByRestaurant findOrdersByRestaurant;

    @Autowired
    private UpdateOrderStatus updateOrderStatus;

    @PostMapping
    private ResponseEntity<String> save(@RequestBody SaveOrderDTO saveOrderDTO, HttpServletRequest httpServletRequest) {
        return saveOrder.execute(new HttpRequestDTO<>(saveOrderDTO, httpServletRequest));
    }

    @PutMapping
    private ResponseEntity<String> updateStatus(@RequestBody UpdateOderDTO updateOderDTO) {
        return updateOrderStatus.execute(updateOderDTO);
    }

    @GetMapping("/consumer/{id}")
    private ResponseEntity<List<FindOrderDTO>> findByConsumer(@PathVariable String id) {
        return findOrdersByConsumer.execute(id);
    }

    @GetMapping("/restaurant/{id}")
    private ResponseEntity<List<FindOrderDTO>> findByRestaurant(@PathVariable String id) {
        return findOrdersByRestaurant.execute(id);
    }

}
