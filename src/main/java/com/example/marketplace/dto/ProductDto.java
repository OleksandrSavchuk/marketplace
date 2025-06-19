package com.example.marketplace.dto;

import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Product DTO")
public class ProductDto {

    @Schema(
            description = "Id",
            example = "1"
    )
    @NotNull(message = "Id must be not null.",
            groups = OnUpdate.class)
    private Long id;

    @Schema(
            description = "Title",
            example = "Ball"
    )
    @NotNull(message = "Title must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Schema(
            description = "Description",
            example = "Small ball"
    )
    @NotNull(message = "Description must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @Schema(
            description = "Price",
            example = "54.99"
    )
    @NotNull(message = "Price must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private Double price;

    @Schema(
            description = "Inventory count",
            example = "18"
    )
    @NotNull(message = "Count must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private Integer inventoryCount;

    @Schema(
            description = "Category id",
            example = "1"
    )
    @NotNull(message = "Category id must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private Long categoryId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> images;

}
