package com.example.marketplace.dto;

import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Cart Item DTO")
public class CartItemDto {

    @Schema(
            description = "Id",
            example = "1"
    )
    @NotNull(message = "Id must be not null.",
            groups = OnUpdate.class)
    private Long id;

    @Schema(
            description = "Product id",
            example = "1"
    )
    @NotNull(message = "Product must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private Long productId;

    @Schema(
            description = "Quantity",
            example = "5"
    )
    @NotNull(message = "Quantity must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private int quantity;

}
