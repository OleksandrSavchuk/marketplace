package com.example.marketplace.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "JWT response")
public class JwtResponse {

    @Schema(
            description = "Id",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Email",
            example = "admin@gmail.com"
    )
    private String username;

    @Schema(
            description = "Access token",
            example = "eyJhbGciOiJIUzUxMiJ9"
                    + ".eyJzdWIiOiJzYXNoYXNhd"
                    + "mNodWsxOTM1QGdtYWlsLmNv"
                    + "bSIsImlkIjo0LCJyb2xlIjo"
                    + "iUk9MRV9BRE1JTiIsImlhdC"
                    + "I6MTc0OTczMTMyMywiZXhwIj"
                    + "oxNzQ5NzM0OTIzfQ."
                    + "UggKj7rPm8tjDAAyS6P0aOZD"
                    + "RBZei-tXUT-pKql0pFT9WS4_L"
                    + "xKZL4ie-MCxpNV2FZZXxFhaBq4CTj-iAegcvg"
    )
    private String accessToken;

    @Schema(
            description = "Refresh token",
            example = "eyJhbGciOiJIUzUxMiJ9"
                    + ".eyJzdWIiOiJzYXNoYXN"
                    + "hdmNodWsxOTM1QGdtYWls"
                    + "LmNvbSIsImlkIjo0LCJpYX"
                    + "QiOjE3NDk3MzEzMjMsImV4"
                    + "cCI6MTc1MjMyMzMyM30"
                    + ".eBa68jqTy_2fPQORawlsD"
                    + "oqtGrdbyVggmU9eJrYWflScP"
                    + "4vY-VvM6Dnt-QxO4IFCPDaQL3OZGWaOo4KcscVqMA"
    )
    private String refreshToken;

}
