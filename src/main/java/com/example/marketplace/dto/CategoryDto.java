package com.example.marketplace.dto;

import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Category DTO")
public class CategoryDto {

    @Schema(
            description = "Id",
            example = "1"
    )
    @NotNull(message = "Id must be not null.",
            groups = OnUpdate.class)
    private Long id;

    @Schema(
            description = "Title",
            example = "Sport"
    )
    @NotNull(message = "Title must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String title;

}
