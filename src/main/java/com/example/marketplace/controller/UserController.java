package com.example.marketplace.controller;

import com.example.marketplace.dto.UserDto;
import com.example.marketplace.entity.User;
import com.example.marketplace.mapper.UserMapper;
import com.example.marketplace.service.UserService;
import com.example.marketplace.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDto> getAll() {
        List<User> users = userService.getAll();
        return userMapper.toDto(users);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @PutMapping
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userService.update(user);
        return userMapper.toDto(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
