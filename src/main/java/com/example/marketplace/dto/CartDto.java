package com.example.marketplace.dto;

import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    @NotNull(message = "Id must be not null.",
            groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "User must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private UserDto user;

    @NotNull(message = "Cart items must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private List<CartItemDto> cartItems;

}
