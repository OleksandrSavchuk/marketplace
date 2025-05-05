package com.example.marketplace.service;

import com.example.marketplace.dto.UserDto;
import com.example.marketplace.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Long id);

    User getByUsername(String username);

    UserDto registerUser(UserDto userDto);

    User update(User user);

    void delete(Long id);

}
