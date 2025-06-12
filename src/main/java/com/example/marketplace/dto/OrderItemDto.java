package com.example.marketplace.dto;

import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Order Item DTO")
public class OrderItemDto {

    @Schema(
            description = "Id",
            example = "1"
    )
    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;

    @Schema(
            description = "Order id",
            example = "1"
    )
    @NotNull(message = "Order id must be not null.", groups = {OnCreate.class, OnUpdate.class})
    private Long orderId;

    @Schema(
            description = "Product id",
            example = "1"
    )
    @NotNull(message = "Product id must be not null.", groups = {OnCreate.class, OnUpdate.class})
    private Long productId;

    @Schema(
            description = "Quantity",
            example = "15"
    )
    @NotNull(message = "Quantity must be not null.", groups = {OnCreate.class, OnUpdate.class})
    private int quantity;

    @Schema(
            description = "Price at purchase",
            example = "999.0"
    )
    @NotNull(message = "Price at purchase must be not null.", groups = {OnCreate.class, OnUpdate.class})
    private double priceAtPurchase;

}
