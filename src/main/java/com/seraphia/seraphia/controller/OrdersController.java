package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.dto.OrderRequest;
import com.seraphia.seraphia.model.Orders;
import com.seraphia.seraphia.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/orders")
@AllArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping
    public List<Orders> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping(path = "{userId}")
    public Orders getOrderById(@PathVariable("userId") Long id) {
        return ordersService.getOrderById(id);
    }

    @PostMapping
    public Orders addOrder(@RequestBody Orders orders) {
        return ordersService.addOrder(orders);
    }

    @PutMapping(path = "{userId}")
    public Orders updateOrderById(@PathVariable("userId") Long id, @RequestBody Orders orderUpdated) {
        return ordersService.updateOrderById(id, orderUpdated);
    }

}
