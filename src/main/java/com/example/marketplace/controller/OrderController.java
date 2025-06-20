package com.example.marketplace.controller;

import com.example.marketplace.dto.OrderDto;
import com.example.marketplace.entity.enums.OrderStatus;
import com.example.marketplace.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Order Controller",
        description = "Order API"
)
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Get list of orders")
    public List<OrderDto> getAll(Principal principal) {
        return orderService.getAll(principal);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    public OrderDto getById(@PathVariable Long id, Principal principal) {
        return orderService.getById(id, principal);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @Operation(summary = "Create order from cart")
    public OrderDto createFromCart(Principal principal) {
        return orderService.createFromCart(principal);
    }

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping("/{id}/status")
    @Operation(summary = "Update order status")
    public OrderDto updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatus status, Principal principal) {
       return orderService.updateStatus(id, status, principal);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order")
    public void delete(@PathVariable Long id, Principal principal) {
        orderService.delete(id, principal);
    }

}
