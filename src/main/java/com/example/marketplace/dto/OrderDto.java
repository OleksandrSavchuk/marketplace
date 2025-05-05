package com.example.marketplace.dto;

import com.example.marketplace.entity.enums.OrderStatus;
import com.example.marketplace.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Total price must be not null.")
    private double totalPrice;

    @NotNull(message = "Items must be not null.")
    private List<OrderItemDto> items;

    @NotNull(message = "Status must be not null.")
    private OrderStatus status;

    private Long buyerId;

    private Long sellerId;

}
