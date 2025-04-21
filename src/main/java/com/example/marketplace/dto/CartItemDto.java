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
    private Long cartId;

    @NotNull(message = "Product must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private Long productId;

    @NotNull(message = "Quantity must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private int quantity;

}
