package com.example.marketplace.dto;

import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemDto {

    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Order id must be not null.", groups = {OnCreate.class, OnUpdate.class})
    private Long orderId;

    @NotNull(message = "Product id must be not null.", groups = {OnCreate.class, OnUpdate.class})
    private Long productId;

    @NotNull(message = "Quantity must be not null.", groups = {OnCreate.class, OnUpdate.class})
    private int quantity;

    @NotNull(message = "Price at purchase must be not null.", groups = {OnCreate.class, OnUpdate.class})
    private double priceAtPurchase;

}
