package com.example.marketplace.controller;

import com.example.marketplace.dto.OrderDto;
import com.example.marketplace.entity.Order;
import com.example.marketplace.mapper.OrderMapper;
import com.example.marketplace.service.OrderService;
import com.example.marketplace.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @GetMapping
    public List<OrderDto> getAll() {
        List<Order> orders = orderService.getAll();
        return orderMapper.toDto(orders);
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        Order order = orderService.getById(id);
        return orderMapper.toDto(order);
    }

    @PostMapping
    public OrderDto createFromCart(Principal principal) {
        Order savedOrder = orderService.createFromCart(principal.getName());
        return orderMapper.toDto(savedOrder);
    }

    @PutMapping
    public OrderDto update(@Validated(OnUpdate.class) @RequestBody OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order updatedOrder = orderService.update(order);
        return orderMapper.toDto(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

}
