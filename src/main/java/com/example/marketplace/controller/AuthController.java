package com.example.marketplace.controller;

import com.example.marketplace.dto.UserDto;
import com.example.marketplace.entity.User;
import com.example.marketplace.jwt.JwtRequest;
import com.example.marketplace.jwt.JwtResponse;
import com.example.marketplace.mapper.UserMapper;
import com.example.marketplace.service.AuthService;
import com.example.marketplace.service.UserService;
import com.example.marketplace.validation.OnCreate;
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
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@Validated @RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
