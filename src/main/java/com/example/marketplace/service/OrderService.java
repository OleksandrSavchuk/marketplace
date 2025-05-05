package com.example.marketplace.service;

import com.example.marketplace.dto.OrderDto;

import java.security.Principal;
import java.util.List;

public interface OrderService {

    List<OrderDto> getAll(Principal principal);

    OrderDto getById(Long id, Principal principal);

    OrderDto createFromCart(Principal principal);

    void delete(Long id, Principal principal);

}
