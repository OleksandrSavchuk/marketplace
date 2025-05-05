package com.example.marketplace.controller;

import com.example.marketplace.dto.OrderDto;
import com.example.marketplace.service.OrderService;
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

    @GetMapping
    public List<OrderDto> getAll(Principal principal) {
        return orderService.getAll(principal);
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id, Principal principal) {
        return orderService.getById(id, principal);
    }

    @PostMapping
    public OrderDto createFromCart(Principal principal) {
        return orderService.createFromCart(principal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Principal principal) {
        orderService.delete(id, principal);
    }

}
