package com.example.marketplace.dto;

import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemDto {

    @NotNull(message = "Id must be not null.",
            groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Cart must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private CartDto cart;

    @NotNull(message = "Product must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private ProductDto product;

    @NotNull(message = "Quantity must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private int quantity;

    @NotNull(message = "Price must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private double price;

}
