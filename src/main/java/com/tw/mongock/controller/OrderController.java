package com.tw.mongock.controller;

import com.tw.mongock.domain.Order;
import com.tw.mongock.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/save")
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return orderService.getOrderById(id);
    }
}
