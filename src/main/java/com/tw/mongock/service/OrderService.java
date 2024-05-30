package com.tw.mongock.service;

import com.tw.mongock.domain.Order;
import com.tw.mongock.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(Order user) {
        return orderRepository.save(user);
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

}
