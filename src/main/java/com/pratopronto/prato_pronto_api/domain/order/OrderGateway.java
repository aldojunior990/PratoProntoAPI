package com.pratopronto.prato_pronto_api.domain.order;

import java.util.List;

public interface OrderGateway {

    boolean save(Order order);

    boolean updateStatus(String orderID, String status);

    List<Order> findAllByConsumerID(String id);

    List<Order> findAllByRestaurantID(String id);

    Order findById(String id);
}
