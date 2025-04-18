package com.example.marketplace.service.impl;

import com.example.marketplace.entity.Order;
import com.example.marketplace.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getAll() {
        return List.of();
    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public Order create(Order product) {
        return null;
    }

    @Override
    public Order update(Order product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
