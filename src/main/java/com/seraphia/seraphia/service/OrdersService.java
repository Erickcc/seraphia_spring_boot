package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> getAllOrders();
    Orders getOrderById(Long id);
    Orders addOrder(Orders order);
    Orders deleteOrderById(Long id);
    Orders updateOrderById(Long id, Orders orderUpdated);
}
