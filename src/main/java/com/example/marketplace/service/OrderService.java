package com.example.marketplace.service;

import com.example.marketplace.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    Order getById(Long id);

    Order createFromCart(String username);

    Order update(Order product);

    void delete(Long id);

}
