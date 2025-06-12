package com.example.marketplace.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request for login")
public class JwtRequest {

    @Schema(
            description = "Email",
            example = "admin@gmail.com"
    )
    @NotNull(message = "Username must be not null.")
    private String username;

    @Schema(
            description = "Password",
            example = "admin"
    )
    @NotNull(message = "Password must be not null.")
    private String password;

}
