package com.example.marketplace.dto;

import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {

    @NotNull(message = "Id must be not null.",
            groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Title must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @NotNull(message = "Description must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @NotNull(message = "Price must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private Double price;

    @NotNull(message = "Count must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private Integer inventoryCount;

    @NotNull(message = "Category id must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private Long categoryId;

}
