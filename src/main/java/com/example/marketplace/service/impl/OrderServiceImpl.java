package com.example.marketplace.service.impl;

import com.example.marketplace.entity.Order;
import com.example.marketplace.repository.OrderRepository;
import com.example.marketplace.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order create(Order product) {
        return orderRepository.save(product);
    }

    @Override
    public Order update(Order product) {
        return orderRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
