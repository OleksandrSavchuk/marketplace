package com.example.marketplace.controller;

import com.example.marketplace.dto.UserDto;
import com.example.marketplace.jwt.JwtRequest;
import com.example.marketplace.jwt.JwtResponse;
import com.example.marketplace.mapper.UserMapper;
import com.example.marketplace.service.AuthService;
import com.example.marketplace.service.UserService;
import com.example.marketplace.validation.OnCreate;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Auth Controller",
        description = "Auth API"
)
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@Validated @RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
