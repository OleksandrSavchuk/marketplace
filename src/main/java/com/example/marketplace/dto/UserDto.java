package com.example.marketplace.dto;

import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "User DTO")
public class UserDto {

    @Schema(
            description = "Id",
            example = "1"
    )
    @NotNull(message = "Id must be not null.",
            groups = OnUpdate.class)
    private Long id;

    @Schema(
            description = "First name",
            example = "Oleksandr"
    )
    @NotNull(message = "First name must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String firstName;

    @Schema(
            description = "Last name",
            example = "Savchuk"
    )
    @NotNull(message = "Last name must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String lastName;

    @Schema(
            description = "Email",
            example = "admin@gmail.com"
    )
    @NotNull(message = "Email must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @Schema(
            description = "Role",
            example = "ROLE_ADMIN"
    )
    @NotNull(message = "Role must be not null.",
            groups = OnCreate.class)
    private String role;

    @Schema(
            description = "Password",
            example = "admin"
    )
    @NotNull(message = "Password must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(
            description = "Password confirmation",
            example = "admin"
    )
    @NotNull(message = "Password confirmation must be not null.",
            groups = OnCreate.class)
    private String passwordConfirmation;

}
