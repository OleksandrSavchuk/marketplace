package com.example.marketplace.service;

import com.example.marketplace.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Long id);

    User getByUsername(String username);

    User createUser(User user);

    User createAdmin(User user);

    User createSeller(User user);

    User update(User user);

    void delete(Long id);

}
