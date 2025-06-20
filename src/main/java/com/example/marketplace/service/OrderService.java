package com.example.marketplace.service;

import com.example.marketplace.dto.OrderDto;
import com.example.marketplace.entity.enums.OrderStatus;

import java.security.Principal;
import java.util.List;

public interface OrderService {

    List<OrderDto> getAll(Principal principal);

    OrderDto getById(Long id, Principal principal);

    OrderDto createFromCart(Principal principal);

    OrderDto updateStatus(Long id, OrderStatus status, Principal principal);

    void delete(Long id, Principal principal);

}
