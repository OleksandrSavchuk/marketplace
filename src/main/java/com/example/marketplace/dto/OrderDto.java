package com.example.marketplace.dto;

import com.example.marketplace.entity.enums.OrderStatus;
import com.example.marketplace.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Order DTO")
public class OrderDto {

    @Schema(
            description = "Id",
            example = "1"
    )
    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;

    @Schema(
            description = "Total price",
            example = "999.0"
    )
    @NotNull(message = "Total price must be not null.")
    private double totalPrice;

    @NotNull(message = "Items must be not null.")
    private List<OrderItemDto> items;

    @Schema(
            description = "Status",
            example = "Processing"
    )
    @NotNull(message = "Status must be not null.")
    private OrderStatus status;

    @Schema(
            description = "Buyer id",
            example = "1"
    )
    private Long buyerId;

    @Schema(
            description = "Seller id",
            example = "1"
    )
    private Long sellerId;

}
