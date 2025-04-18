package com.example.marketplace.dto;

import com.example.marketplace.entity.enums.OrderStatus;
import com.example.marketplace.validation.OnUpdate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Total price must be not null.")
    private double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemDto> items;

    @NotNull(message = "Status must be not null.")
    private OrderStatus status;

}
