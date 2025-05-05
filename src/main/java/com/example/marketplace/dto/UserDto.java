package com.example.marketplace.dto;

import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

    @NotNull(message = "Id must be not null.",
            groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "First name must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String firstName;

    @NotNull(message = "Last name must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String lastName;

    @NotNull(message = "Email must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @NotNull(message = "Role must be not null.",
            groups = OnCreate.class)
    private String role;

    @NotNull(message = "Password must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "Password confirmation must be not null.",
            groups = OnCreate.class)
    private String passwordConfirmation;

}
